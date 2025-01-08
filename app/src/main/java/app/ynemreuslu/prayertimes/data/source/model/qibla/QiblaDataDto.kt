package app.ynemreuslu.prayertimes.data.source.model.qibla

import app.ynemreuslu.prayertimes.data.source.model.prayer.PrayerDataDto

data class QiblaDataDto(
    val code: Int,
    val status: String,
    val data: QiblaData
)
