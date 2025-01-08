package app.ynemreuslu.prayertimes.common

import android.R.attr.priority
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager

import android.os.Looper
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resumeWithException



class LocationTracker @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient
) {
    private val locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = 5000
        fastestInterval = 3000
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(): Flow<Location> = flow {
        val lastLocation = fusedLocationClient.lastLocation.await()
        lastLocation?.let { emit(it) }

        val locationUpdates = suspendCancellableCoroutine { continuation ->
            val callback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    result.lastLocation?.let { location ->
                        continuation.resumeWith(Result.success(location))
                    }
                }
            }

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                callback,
                Looper.getMainLooper()
            ).addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }

            continuation.invokeOnCancellation {
                fusedLocationClient.removeLocationUpdates(callback)
            }
        }
        emit(locationUpdates)
    }.catch { e ->
        Log.e("LocationTracker", "Location error: ${e.message}")
        throw e
    }
}