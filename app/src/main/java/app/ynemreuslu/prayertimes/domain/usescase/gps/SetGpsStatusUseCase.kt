package app.ynemreuslu.prayertimes.domain.usescase.gps

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class SetGpsStatusUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    suspend operator fun invoke(isGpsDisabled: Boolean) {
        repository.setGpsStatus(isGpsDisabled)
    }
}