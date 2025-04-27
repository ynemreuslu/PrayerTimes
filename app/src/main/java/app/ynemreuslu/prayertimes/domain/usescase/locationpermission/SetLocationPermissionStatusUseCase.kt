package app.ynemreuslu.prayertimes.domain.usescase.locationpermission

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class SetLocationPermissionStatusUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    suspend operator fun invoke(status: String) {
        repository.setLocationPermissionStatus(status)
    }
}