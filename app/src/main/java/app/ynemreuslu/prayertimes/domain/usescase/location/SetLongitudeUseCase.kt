package app.ynemreuslu.prayertimes.domain.usescase.location

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class SetLongitudeUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    operator fun invoke(longitude: Double) {
        repository.fetchLongitude(longitude)
    }
}