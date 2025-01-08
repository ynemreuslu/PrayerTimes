package app.ynemreuslu.prayertimes.domain.usescase

import app.ynemreuslu.prayertimes.data.source.model.qibla.QiblaDataDto
import app.ynemreuslu.prayertimes.domain.repository.QiblaRepository


import app.ynemreuslu.prayertimes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQiblaUseCase @Inject constructor(
    private val qiblaRepository: QiblaRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Flow<Resource<QiblaDataDto>> = flow {
        try {
            val response = qiblaRepository.getQiblaDirection(longitude,latitude)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}