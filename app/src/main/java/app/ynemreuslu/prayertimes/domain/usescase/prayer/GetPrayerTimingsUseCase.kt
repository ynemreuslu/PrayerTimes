package app.ynemreuslu.prayertimes.domain.usescase.prayer


import app.ynemreuslu.prayertimes.domain.prayer.PrayerTimings
import app.ynemreuslu.prayertimes.domain.repository.PrayerTimeRepository
import app.ynemreuslu.prayertimes.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPrayerTimingsUseCase @Inject constructor(
    private val prayerRepository: PrayerTimeRepository
) {
    suspend operator fun invoke(
        date: String,
        address: String,
        method: Int
    ): Flow<Resource<PrayerTimings>> = flow {
        try {
            val response = prayerRepository.getPrayerTimesForDate(date, address, method)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}

