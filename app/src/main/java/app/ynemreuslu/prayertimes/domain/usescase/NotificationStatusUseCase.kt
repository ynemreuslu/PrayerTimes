package app.ynemreuslu.prayertimes.domain.usescase


import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class NotificationStatusUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {

    suspend operator fun invoke(status: String) {
        repository.setNotificationPermissionStatus(status)
    }
}