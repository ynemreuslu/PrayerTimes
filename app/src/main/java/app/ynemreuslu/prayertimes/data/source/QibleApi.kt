package app.ynemreuslu.prayertimes.data.source

import app.ynemreuslu.prayertimes.data.source.model.qibla.QiblaDataDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QibleApi {
    @GET("qibla/{latitude}/{longitude}")
    suspend fun getQiblaDirection(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): QiblaDataDto
}