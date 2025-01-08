package app.ynemreuslu.prayertimes.domain.usescase

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import jakarta.inject.Inject

class GpsControllerUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    suspend operator fun invoke(isGpsDisabled: Boolean) {
        repository.setGpsStatus(isGpsDisabled)
    }

     fun isGpsDisabled(): Boolean {
        return repository.isGpsDisabled()
    }
}