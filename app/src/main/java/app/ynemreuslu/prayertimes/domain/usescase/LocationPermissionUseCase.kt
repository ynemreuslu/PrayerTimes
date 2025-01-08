package app.ynemreuslu.prayertimes.domain.usescase


import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class LocationPermissionUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {

    suspend operator fun invoke(isGranted: Boolean) {
        repository.setLocationPermissionGrantedStatus(isGranted)
    }

     fun checkPermissionGranted(): Boolean {
        return repository.isLocationPermissionGranted()
    }


    suspend operator fun invoke(status: String) {
        repository.setLocationPermissionStatus(status)
    }

    suspend fun fetchPermissionStatus(): String {
        return repository.getLocationPermissionStatus()
    }

}