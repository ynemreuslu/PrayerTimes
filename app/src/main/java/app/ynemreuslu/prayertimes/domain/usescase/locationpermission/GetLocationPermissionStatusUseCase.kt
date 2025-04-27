package app.ynemreuslu.prayertimes.domain.usescase.locationpermission

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class GetLocationPermissionStatusUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    suspend operator fun invoke(): String {
        return repository.getLocationPermissionStatus()
    }
}