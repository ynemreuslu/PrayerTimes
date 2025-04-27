package app.ynemreuslu.prayertimes.domain.usescase.gps

import javax.inject.Inject

data class GpsUseCases @Inject constructor(
    val setGpsStatus: SetGpsStatusUseCase,
    val isGpsDisabled: IsGpsDisabledUseCase
)