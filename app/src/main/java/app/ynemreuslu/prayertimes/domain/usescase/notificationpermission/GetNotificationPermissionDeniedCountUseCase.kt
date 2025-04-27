package app.ynemreuslu.prayertimes.domain.usescase.notificationpermission

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class GetNotificationPermissionDeniedCountUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    suspend operator fun invoke(): Int {
        return repository.getNotificationPermissionDeniedCount()
    }
}