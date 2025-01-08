package app.ynemreuslu.prayertimes.domain.repository


interface LocalPermissionManagerRepository {

    // Location Permission
    fun isLocationPermissionGranted(): Boolean
    suspend fun setLocationPermissionGrantedStatus(isGranted: Boolean)
    suspend fun fetchLatitude(latitude: Double)
    suspend fun getLatitude() : Double
    suspend fun fetchLongitude(longitude: Double)
    suspend fun getLongitude() : Double
    suspend fun getLocationPermissionStatus(): String
    fun isGpsDisabled(): Boolean
    suspend fun setGpsStatus(isDisabled: Boolean)
    suspend fun setLocationPermissionStatus(status: String)

    // Notification Permission
    fun isNotificationPermissionGranted(): Boolean
    suspend fun setNotificationPermissionGrantedStatus(isGranted: Boolean)
    suspend fun getNotificationPermissionStatus(): String
    suspend fun setNotificationPermissionStatus(status: String)
    suspend fun getNotificationPermissionDeniedCount(): Int
    suspend fun incrementNotificationPermissionDeniedCount()
    suspend fun resetNotificationPermissionDeniedCount()

    // Skip Button State
    fun isSkipButtonEnabled(): Boolean
    suspend fun setSkipButtonState(isEnabled: Boolean)
}
