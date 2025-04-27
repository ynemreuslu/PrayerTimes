package app.ynemreuslu.prayertimes.ui.notificationpermission


import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.content.Context
import android.os.Build
import app.ynemreuslu.prayertimes.common.hasNotificationPermissionDenied
import app.ynemreuslu.prayertimes.common.hasNotificationPermissionGranted
import app.ynemreuslu.prayertimes.common.shouldShowNotificationPermissionRationale
import app.ynemreuslu.prayertimes.domain.usescase.notificationpermission.NotificationStatusUseCase
import app.ynemreuslu.prayertimes.domain.usescase.notificationpermission.NotificationPermissionUseCases
import app.ynemreuslu.prayertimes.domain.usescase.skipbutton.SkipButtonUseCases
import app.ynemreuslu.prayertimes.ui.notificationpermission.NotificationPermissionContract.RequestStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class NotificationPermissionViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val notificationPermissionUseCase: NotificationPermissionUseCases,
    private val skipButtonUseCase: SkipButtonUseCases,
    private val notificationStatusUseCase: NotificationStatusUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationPermissionContract.UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<NotificationPermissionContract.UiEffect>() }
    val uiEffect: Flow<NotificationPermissionContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        checkIfSkipped()
        checkPermissionGranted()
    }

    private fun checkIfSkipped() {
        if (skipButtonUseCase.isSkipButtonEnabled()) {
            navigateToHomeScreen()
        }
    }
    private fun checkPermissionGranted() {
        viewModelScope.launch {
            if (_uiState.value.isPermissionGranted == true) {
                navigateToHomeScreen()
            }
        }
    }

    fun onAction(action: NotificationPermissionContract.UiAction) {
        when (action) {
            is NotificationPermissionContract.UiAction.RequestPermission -> handlePermissionRequest()
            is NotificationPermissionContract.UiAction.OpenAppSettings -> openNotificationSettings()
            is NotificationPermissionContract.UiAction.SkipPermission -> handleSkip()
        }
    }

    private fun handlePermissionRequest() {
        viewModelScope.launch {
            val isGranted = applicationContext.hasNotificationPermissionGranted()
            val isPermanentlyDenied = !shouldShowRequestPermissionRationale()
            val permissionDeniedCount = notificationPermissionUseCase.getNotificationPermissionDeniedCount()


            val newStatus = when {
                isGranted -> RequestStatus.GRANTED
                isPermanentlyDenied || permissionDeniedCount >= 2 -> RequestStatus.PERMANENTLY_DENIED
                else -> RequestStatus.DENIED
            }

            updateUiState {
                copy(
                    isPermissionGranted = isGranted,
                    requestStatus = newStatus
                )
            }
            notificationStatusUseCase.invoke(newStatus.name)
            notificationPermissionUseCase.setNotificationPermissionGranted(isGranted)
            when (newStatus) {
                RequestStatus.GRANTED -> {
                    navigateToHomeScreen()
                    notificationPermissionUseCase.setNotificationPermissionGranted(true)
                }

                RequestStatus.PERMANENTLY_DENIED -> {
                    openNotificationSettings()
                    emitUiEffect(
                        NotificationPermissionContract.UiEffect.UpdateButtonState
                    )
                    notificationPermissionUseCase.incrementNotificationPermissionDeniedCount()
                }

                RequestStatus.DENIED -> {
                    requestNotificationPermission()
                    notificationPermissionUseCase.incrementNotificationPermissionDeniedCount()
                }

                else -> {
                    requestNotificationPermission()
                }
            }
        }
    }


    private fun shouldShowRequestPermissionRationale(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return true

        return when {
            applicationContext.hasNotificationPermissionGranted() -> true
            applicationContext is Activity && applicationContext.shouldShowNotificationPermissionRationale() -> true
            else -> applicationContext.hasNotificationPermissionDenied()
        }
    }

    private fun handleSkip() {
        viewModelScope.launch {
            saveSkipPreference()
            navigateToHomeScreen()
        }
    }

    private suspend fun saveSkipPreference() {
        skipButtonUseCase.setSkipButtonState(true)
    }

    private fun openNotificationSettings() {
        viewModelScope.launch {
            emitUiEffect(NotificationPermissionContract.UiEffect.NavigateToSettings)
        }
    }

    private fun requestNotificationPermission() {
        viewModelScope.launch {
            emitUiEffect(NotificationPermissionContract.UiEffect.PermissionRequest)
        }
    }

    private fun navigateToHomeScreen() {
        viewModelScope.launch {
            emitUiEffect(NotificationPermissionContract.UiEffect.NavigateToHome)
        }
    }

    private fun updateUiState(block: NotificationPermissionContract.UiState.() -> NotificationPermissionContract.UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(effect: NotificationPermissionContract.UiEffect) {
        _uiEffect.send(effect)
    }
}


