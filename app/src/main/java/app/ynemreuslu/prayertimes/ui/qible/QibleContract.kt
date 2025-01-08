package app.ynemreuslu.prayertimes.ui.qible

import app.ynemreuslu.prayertimes.data.source.model.qibla.QiblaDataDto

object QibleContract {
    data class UiState(
        val qible: QiblaDataDto? = null,
        val error: String? = null,
        val rotation: Float? = null,
        val qiblaDirection: Float? = null,
        val latitude: Double? = null,
        val longitude: Double? = null

    )


    sealed class UiAction {

    }

    sealed class UiEffect {

    }
}