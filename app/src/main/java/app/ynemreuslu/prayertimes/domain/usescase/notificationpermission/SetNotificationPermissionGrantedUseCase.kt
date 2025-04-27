package app.ynemreuslu.prayertimes.domain.usescase.notificationpermission

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class SetNotificationPermissionGrantedUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    suspend operator fun invoke(isGranted: Boolean) {
        repository.setNotificationPermissionGrantedStatus(isGranted)
    }
}