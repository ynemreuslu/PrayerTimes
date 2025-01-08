package app.ynemreuslu.prayertimes.domain.usescase



import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class NotificationPermissionUseCase @Inject constructor(
    private val localPermissionManager: LocalPermissionManagerRepository
) {

    suspend fun invoke(isGranted: Boolean) {
        localPermissionManager.setNotificationPermissionGrantedStatus(isGranted)
    }

     fun checkPermissionGranted(): Boolean {
        return localPermissionManager.isNotificationPermissionGranted()
    }

    suspend fun fetchDeniedCount(): Int {
        return localPermissionManager.getNotificationPermissionDeniedCount()
    }

    suspend fun incrementPermissionDeniedCount() {
        localPermissionManager.incrementNotificationPermissionDeniedCount()
    }

    suspend fun resetPermissionDeniedCount() {
        localPermissionManager.resetNotificationPermissionDeniedCount()
    }
}