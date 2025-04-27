package app.ynemreuslu.prayertimes.domain.usescase.location

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class SetLatitudeUseCase @Inject constructor(
    private val repository: LocalPermissionManagerRepository
) {
    operator fun invoke(latitude: Double) {
        repository.fetchLatitude(latitude)
    }
}