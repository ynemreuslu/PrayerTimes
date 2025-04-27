package app.ynemreuslu.prayertimes.domain.usescase.locationpermission

import javax.inject.Inject

data class LocationPermissionUseCases @Inject constructor(
    val setLocationPermissionGranted: SetLocationPermissionGrantedUseCase,
    val isLocationPermissionGranted: IsLocationPermissionGrantedUseCase,
    val setLocationPermissionStatus: SetLocationPermissionStatusUseCase,
    val getLocationPermissionStatus: GetLocationPermissionStatusUseCase
)