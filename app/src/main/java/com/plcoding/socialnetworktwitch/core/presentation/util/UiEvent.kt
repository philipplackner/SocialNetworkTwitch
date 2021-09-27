package com.plcoding.socialnetworktwitch.core.presentation.util

import com.plcoding.socialnetworktwitch.core.util.UiText

sealed class UiEvent {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
}