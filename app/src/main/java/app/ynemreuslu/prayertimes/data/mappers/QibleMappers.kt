package app.ynemreuslu.prayertimes.data.mappers

import app.ynemreuslu.prayertimes.data.source.model.qibla.QiblaData
import app.ynemreuslu.prayertimes.data.source.model.qibla.QiblaDataDto




fun QiblaData.toQible(): QiblaData {
    return QiblaData(
        latitude = this.latitude,
        longitude = this.longitude,
        direction = this.direction
    )
}

fun QiblaDataDto.toQiblaDataDto(): QiblaDataDto {
    return QiblaDataDto(
        code = this.code,
        status = this.status,
        data = this.data.toQible()
    )
}