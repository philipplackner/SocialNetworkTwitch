package com.plcoding.socialnetworktwitch.core.presentation.util

import com.plcoding.socialnetworktwitch.core.util.Event
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.ramcosta.composedestinations.spec.Direction

sealed class UiEvent: Event() {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate(val direction: Direction) : UiEvent()
    object NavigateUp : UiEvent()
    object OnLogin: UiEvent()
}