package app.ynemreuslu.prayertimes.data.source.model

data class PrayerTimingsDto(
    val code: Int,
    val status: String,
    val data: PrayerDataDto
)