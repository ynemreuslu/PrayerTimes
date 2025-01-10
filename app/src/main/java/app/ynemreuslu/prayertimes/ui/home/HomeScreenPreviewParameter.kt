package app.ynemreuslu.prayertimes.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.ynemreuslu.prayertimes.domain.prayer.PrayerData
import app.ynemreuslu.prayertimes.domain.prayer.PrayerTimings
import app.ynemreuslu.prayertimes.domain.prayer.Timings

class HomeScreenPreviewParameter : PreviewParameterProvider<HomeContract.UiState> {
    override val values: Sequence<HomeContract.UiState>
        get() = sequenceOf(
            HomeContract.UiState(
                isLoading = false,
                location = "İstanbul",
                prayerTimings = PrayerTimings(
                    200,
                    "ok",
                    PrayerData(
                        Timings(
                            "04:30",
                            "06:40",
                            "13:20",
                            "16:30",
                            "18:05",
                            "20:30",
                            "22:30",
                            "23:30",
                            "01:10",
                            "02:20"
                        )
                    )
                ),
                longitude = 38.05,
                latitude = 26.07,
                gregorianDate = "25-06-2023",
                hijriDate = "14 Ramadan 1453",
                prayerIndex = 3,
                prayerSeconds = "07",
                prayerMinutes = "05:",
                prayerHours = "01:"
            )
        )
}
