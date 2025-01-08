package app.ynemreuslu.prayertimes.data.repository


import androidx.collection.arraySetOf
import jakarta.inject.Inject
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import app.ynemreuslu.prayertimes.domain.repository.LocalPermissionManagerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class LocalPermissionManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalPermissionManagerRepository {

    override fun isLocationPermissionGranted(): Boolean {
        return runBlocking {
            dataStore.data.first().let { preferences ->
                preferences[PreferencesKeys.LOCATION_PERMISSION_GRANTED] ?: false
            }
        }
    }

    override suspend fun setLocationPermissionGrantedStatus(isGranted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LOCATION_PERMISSION_GRANTED] = isGranted
        }
    }

    override suspend fun fetchLatitude(latitude: Double) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LATITUDE] = latitude
        }
    }

    override suspend fun getLatitude(): Double {
        return dataStore.data.first().let { preferences ->
            preferences[PreferencesKeys.LATITUDE] ?: 0.0
        }
    }

    override suspend fun fetchLongitude(longitude: Double) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LONGITUDE] = longitude
        }
    }

    override suspend fun getLongitude(): Double {
        return dataStore.data.first().let { preferences ->
            preferences[PreferencesKeys.LONGITUDE] ?: 0.0
        }
    }


    override suspend fun getLocationPermissionStatus(): String {
        return dataStore.data.first().let { preferences ->
            preferences[PreferencesKeys.LOCATION_PERMISSION_STATUS] ?: ""
        }
    }

    override fun isGpsDisabled(): Boolean {
        return runBlocking {
            dataStore.data.first().let { preferences ->
                preferences[PreferencesKeys.GPS_DISABLED] ?: false
            }
        }
    }

    override suspend fun setGpsStatus(isDisabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.GPS_DISABLED] = isDisabled
        }
    }

    override suspend fun setLocationPermissionStatus(status: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LOCATION_PERMISSION_STATUS] = status
        }
    }

    override fun isNotificationPermissionGranted(): Boolean {
        return runBlocking {
            dataStore.data.first().let { preferences ->
                preferences[PreferencesKeys.NOTIFICATION_PERMISSION_GRANTED] ?: false
            }
        }
    }

    override suspend fun setNotificationPermissionGrantedStatus(isGranted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.NOTIFICATION_PERMISSION_GRANTED] = isGranted
        }
    }

    override suspend fun getNotificationPermissionDeniedCount(): Int {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferencesKeys.NOTIFICATION_PERMISSION_DENIED_COUNT] ?: 0
            }
            .first()
    }

    override suspend fun incrementNotificationPermissionDeniedCount() {
        dataStore.edit { preferences ->
            val currentCount =
                preferences[PreferencesKeys.NOTIFICATION_PERMISSION_DENIED_COUNT] ?: 0
            preferences[PreferencesKeys.NOTIFICATION_PERMISSION_DENIED_COUNT] = currentCount + 1
        }
    }

    override suspend fun resetNotificationPermissionDeniedCount() {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.NOTIFICATION_PERMISSION_DENIED_COUNT] = 0
        }
    }

    override suspend fun getNotificationPermissionStatus(): String {
        return dataStore.data.first().let { preferences ->
            preferences[PreferencesKeys.NOTIFICATION_PERMISSION_STATUS] ?: ""
        }
    }

    override suspend fun setNotificationPermissionStatus(status: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.NOTIFICATION_PERMISSION_STATUS] = status
        }
    }

    override fun isSkipButtonEnabled(): Boolean {
        return runBlocking {
            dataStore.data.first().let { preferences ->
                preferences[PreferencesKeys.SKIP_BUTTON_ENABLED] ?: false
            }
        }
    }

    override suspend fun setSkipButtonState(isEnabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SKIP_BUTTON_ENABLED] = isEnabled
        }
    }

    private object PreferencesKeys {
        val LOCATION_PERMISSION_GRANTED = booleanPreferencesKey("location_permission_granted")
        val NOTIFICATION_PERMISSION_GRANTED =
            booleanPreferencesKey("notification_permission_granted")
        val SKIP_BUTTON_ENABLED = booleanPreferencesKey("skip_button_enabled")
        val LATITUDE = doublePreferencesKey("latitude")
        val LONGITUDE = doublePreferencesKey("longitude")
        val NOTIFICATION_PERMISSION_STATUS = stringPreferencesKey("notification_permission_status")
        val LOCATION_PERMISSION_STATUS = stringPreferencesKey("location_permission_status")
        val NOTIFICATION_PERMISSION_DENIED_COUNT =
            intPreferencesKey("notification_permission_denied_count")
        val GPS_DISABLED = booleanPreferencesKey("gps_disabled")
    }
}
