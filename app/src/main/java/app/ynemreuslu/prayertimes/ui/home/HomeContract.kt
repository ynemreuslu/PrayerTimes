package app.ynemreuslu.prayertimes.ui.home

import app.ynemreuslu.prayertimes.domain.prayer.PrayerTimings
import java.time.LocalDate
import java.util.Date


object HomeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val prayerTimings: PrayerTimings? = null,
        val errorPrayerTimings: String? = null,
        val location: String? = null,
        val latitude: Double? = null,
        val longitude: Double? = null,
        val gregorianDate: String? = null,
        val hijriDate: String? = null,
        val prayerIndex: Int? = null,
        val currentPrayerTime : String? = null,
        val prayerHours : String? = null,
        val prayerMinutes : String? = null,
        val prayerSeconds : String? = null,
    )


    sealed class UiAction {
        object NavigateToMap : UiAction()
    }

    sealed class UiEffect {
        data class ShowPrayerTimesForDate(val date: LocalDate) : UiEffect()
        object RequestLocationPermission : UiEffect()
    }
}