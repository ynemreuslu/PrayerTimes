package app.ynemreuslu.prayertimes.domain.usescase

import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(private val repository: LocalPermissionManagerRepository) {

      fun fetchLatitude(latitude: Double) {
        repository.fetchLatitude(latitude)
    }
      fun fetchLongitude(longitude: Double) {
        repository.fetchLongitude(longitude)
    }
     fun getLatitude() : Double {
        return repository.getLatitude()
    }
     fun getLongitude() : Double {
        return repository.getLongitude()
    }

}