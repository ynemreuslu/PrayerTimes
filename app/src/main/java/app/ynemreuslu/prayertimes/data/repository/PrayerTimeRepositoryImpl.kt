package app.ynemreuslu.prayertimes.data.repository

import app.ynemreuslu.prayertimes.data.mappers.toPrayerTimings
import app.ynemreuslu.prayertimes.data.source.PrayerTimeApi
import app.ynemreuslu.prayertimes.domain.prayer.PrayerTimings
import app.ynemreuslu.prayertimes.domain.repository.PrayerTimeRepository
import javax.inject.Inject


class PrayerTimeRepositoryImpl @Inject constructor(private val prayerTimeApi: PrayerTimeApi) :
    PrayerTimeRepository {
    override suspend fun getPrayerTimesForDate(
        date: String,
        location: String,
        method: Int
    ): PrayerTimings {
        return prayerTimeApi.getPrayerTimesForDate(date, location, method).toPrayerTimings()
    }

}