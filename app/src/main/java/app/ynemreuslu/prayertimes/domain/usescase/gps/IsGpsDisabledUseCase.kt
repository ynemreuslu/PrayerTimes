package app.ynemreuslu.prayertimes.domain.usescase.gps

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class IsGpsDisabledUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    operator fun invoke(): Boolean {
        return repository.isGpsDisabled()
    }
}