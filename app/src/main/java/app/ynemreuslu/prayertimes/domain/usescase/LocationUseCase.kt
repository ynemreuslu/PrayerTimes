package app.ynemreuslu.prayertimes.domain.usescase

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(private val repository: LocalPermissionManagerRepository) {

    suspend  fun fetchLatitude(latitude: Double) {
        repository.fetchLatitude(latitude)
    }
    suspend  fun fetchLongitude(longitude: Double) {
        repository.fetchLongitude(longitude)
    }
    suspend fun getLatitude() : Double {
        return repository.getLatitude()
    }
    suspend fun getLongitude() : Double {
        return repository.getLongitude()
    }

}