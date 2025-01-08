package app.ynemreuslu.prayertimes.ui.qible

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ynemreuslu.prayertimes.domain.usescase.GetQiblaUseCase
import app.ynemreuslu.prayertimes.domain.usescase.LocationUseCase
import app.ynemreuslu.prayertimes.util.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class QibleViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getQiblaUseCase: GetQiblaUseCase,
    private val locationUseCase: LocationUseCase,
    private val compassQibla: CompassQibla,
) : ViewModel() {

    private val _uiState = MutableStateFlow(QibleContract.UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = Channel<QibleContract.UiEffect>()
    val uiEffect: Flow<QibleContract.UiEffect> = _uiEffect.receiveAsFlow()

    init {
        updateRotation()
        updateLocation()
    }

    fun updateLocation() {
        viewModelScope.launch {
            val lat = locationUseCase.getLatitude()
            val long = locationUseCase.getLongitude()
            compassQibla.calculateQiblaDirection(lat, long)
            updateUiState {
                copy(
                    latitude = lat,
                    longitude = long,
                    qiblaDirection = compassQibla.getQiblaDirection()

                )
            }
        }
    }

    fun updateRotation() {
        compassQibla.startListening()
        compassQibla.setOnRotationChangedListener {
            updateUiState {
                copy(
                    rotation = it
                )
            }

        }
    }

    private suspend fun emitUiEffect(effect: QibleContract.UiEffect) {
        _uiEffect.send(effect)
    }

    private fun updateUiState(update: QibleContract.UiState.() -> QibleContract.UiState) {
        _uiState.update(update)
    }

    override fun onCleared() {
        super.onCleared()
        compassQibla.stopListening()

    }
}