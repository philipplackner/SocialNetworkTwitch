package com.plcoding.socialnetworktwitch.core.domain.states

import com.plcoding.socialnetworktwitch.core.util.Error

data class PasswordTextFieldState(
    val text: String = "",
    val error: Error? = null,
    val isPasswordVisible: Boolean = false
)
