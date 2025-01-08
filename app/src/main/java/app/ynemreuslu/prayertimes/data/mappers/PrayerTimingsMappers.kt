package app.ynemreuslu.prayertimes.data.mappers

import app.ynemreuslu.prayertimes.data.source.model.prayer.PrayerDataDto
import app.ynemreuslu.prayertimes.data.source.model.prayer.PrayerTimingsDto
import app.ynemreuslu.prayertimes.data.source.model.prayer.TimingsDto
import app.ynemreuslu.prayertimes.domain.prayer.PrayerData
import app.ynemreuslu.prayertimes.domain.prayer.PrayerTimings
import app.ynemreuslu.prayertimes.domain.prayer.Timings


fun TimingsDto.toTimings(): Timings {
    return Timings(
        fajrTime = this.fajrTime,
        sunriseTime = this.sunriseTime,
        dhuhrTime = this.dhuhrTime,
        asrTime = this.asrTime,
        sunsetTime = this.sunsetTime,
        maghribTime = this.maghribTime,
        ishaTime = this.ishaTime,
        midnightTime = this.midnightTime,
        firstThirdTime = this.firstThirdTime,
        lastThirdTime = this.lastThirdTime
    )
}

fun PrayerDataDto.toPrayerData(): PrayerData {
    return PrayerData(
        timings = this.timings.toTimings()
    )
}

fun PrayerTimingsDto.toPrayerTimings(): PrayerTimings {
    return PrayerTimings(
        code = this.code,
        status = this.status,
        data = this.data.toPrayerData()
    )
}