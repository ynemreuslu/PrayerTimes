package app.ynemreuslu.prayertimes.ui.qible



object QibleContract {
    data class UiState(
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