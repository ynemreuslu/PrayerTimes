package app.ynemreuslu.prayertimes.domain.usescase.location

import app.ynemreuslu.prayertimes.domain.usescase.gps.IsGpsDisabledUseCase
import app.ynemreuslu.prayertimes.domain.usescase.gps.SetGpsStatusUseCase
import javax.inject.Inject

data class LocationUseCases @Inject constructor(
    val setLatitude: SetLatitudeUseCase,
    val setLongitude: SetLongitudeUseCase,
    val getLatitude: GetLatitudeUseCase,
    val getLongitude: GetLongitudeUseCase,
    val setGpsStatus: SetGpsStatusUseCase,
    val isGpsDisabled: IsGpsDisabledUseCase
)