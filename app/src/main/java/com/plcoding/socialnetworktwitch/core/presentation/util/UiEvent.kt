package com.plcoding.socialnetworktwitch.core.presentation.util

import com.plcoding.socialnetworktwitch.core.util.UiText

sealed class UiEvent {
    data class SnackbarEvent(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
}