package app.ynemreuslu.prayertimes.data.repository

import app.ynemreuslu.prayertimes.data.mappers.toQiblaDataDto
import app.ynemreuslu.prayertimes.data.source.QibleApi
import app.ynemreuslu.prayertimes.data.source.model.qibla.QiblaDataDto
import app.ynemreuslu.prayertimes.domain.repository.QiblaRepository
import javax.inject.Inject

class QibleRepositoryImpl @Inject constructor(private val qibleApi: QibleApi) : QiblaRepository {
    override suspend fun getQiblaDirection(
        latitude: Double,
        longitude: Double
    ): QiblaDataDto {
        return qibleApi.getQiblaDirection(latitude,longitude).toQiblaDataDto()
    }

}