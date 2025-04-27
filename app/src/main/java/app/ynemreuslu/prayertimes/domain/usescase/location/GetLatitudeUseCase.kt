package app.ynemreuslu.prayertimes.domain.usescase.location

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class GetLatitudeUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    operator fun invoke(): Double {
        return repository.getLatitude()
    }
}