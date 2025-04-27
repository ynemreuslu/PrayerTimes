package app.ynemreuslu.prayertimes.domain.usescase.notificationpermission

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class IsNotificationPermissionGrantedUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    operator fun invoke(): Boolean {
        return repository.isNotificationPermissionGranted()
    }
}