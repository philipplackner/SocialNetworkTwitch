package com.plcoding.socialnetworktwitch.core.domain.states

import com.plcoding.socialnetworktwitch.core.util.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)
