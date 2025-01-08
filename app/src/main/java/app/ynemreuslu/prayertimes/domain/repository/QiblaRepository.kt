package app.ynemreuslu.prayertimes.domain.repository

import app.ynemreuslu.prayertimes.data.source.model.qibla.QiblaDataDto

interface QiblaRepository {
    suspend fun getQiblaDirection(
        latitude: Double,
        longitude: Double
    ): QiblaDataDto
}