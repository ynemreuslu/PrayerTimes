package app.ynemreuslu.prayertimes.domain.usescase


import app.ynemreuslu.prayertimes.domain.prayer.PrayerTimings
import app.ynemreuslu.prayertimes.domain.repository.PrayerTimeRepository
import app.ynemreuslu.prayertimes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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