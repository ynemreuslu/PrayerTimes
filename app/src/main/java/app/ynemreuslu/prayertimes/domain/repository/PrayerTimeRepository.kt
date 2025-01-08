package app.ynemreuslu.prayertimes.domain.repository

import app.ynemreuslu.prayertimes.domain.prayer.PrayerTimings


interface PrayerTimeRepository {

    suspend fun getPrayerTimesForDate(
        date: String,
        location: String,
        method: Int
    ): PrayerTimings

}