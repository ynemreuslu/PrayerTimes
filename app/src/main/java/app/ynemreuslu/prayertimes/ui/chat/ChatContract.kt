package app.ynemreuslu.prayertimes.ui.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import app.ynemreuslu.prayertimes.domain.prayer.PrayerTimings

object ChatContract {
    data class UiState(
        val isLoading: Boolean = false,
        val userMessage: String = "",
        val isExpanded : Boolean = false,
        val chatMessageState: ChatMessageState
    )

    sealed class UiAction {
        data class  EnterMessage(val message: String) : UiAction()
        data class  SendMessage(val chatMessage: ChatMessage) : UiAction()
    }

    sealed class UiEffect {

    }
}

