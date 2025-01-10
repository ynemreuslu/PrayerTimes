package app.ynemreuslu.prayertimes.ui.notificationpermission

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NotificationPermissionPreviewParameter : PreviewParameterProvider<NotificationPermissionContract.UiState> {
    override val values: Sequence<NotificationPermissionContract.UiState>
        get() = sequenceOf(
            NotificationPermissionContract.UiState(
                isPermissionGranted = false
            )
        )
}
