package app.ynemreuslu.prayertimes.ui.components

import androidx.annotation.DrawableRes
import app.ynemreuslu.prayertimes.R

sealed class BottomNavItem(
    @DrawableRes val iconFill: Int,
    @DrawableRes val iconOutline: Int,
    val route: String,
) {
    data object Home : BottomNavItem(R.drawable.ic_prayer_fill, R.drawable.ic_prayer_outline,"home")
    data object Qible : BottomNavItem(R.drawable.ic_qible_fill, R.drawable.ic_qible_outline, "qible")
    data object Chat : BottomNavItem(R.drawable.ic_chat_fill, R.drawable.ic_chat, "chat")
    data object Settings : BottomNavItem(R.drawable.ic_settings, R.drawable.ic_settings_fill, "settings")

}

