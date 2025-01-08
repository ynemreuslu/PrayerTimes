package app.ynemreuslu.prayertimes.data.source.model.prayer

data class PrayerTimingsDto(
    val code: Int,
    val status: String,
    val data: PrayerDataDto
)