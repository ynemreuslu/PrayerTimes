package app.ynemreuslu.prayertimes.domain.prayer

data class PrayerTimings(
    val code: Int,
    val status: String,
    val data: PrayerData,
)