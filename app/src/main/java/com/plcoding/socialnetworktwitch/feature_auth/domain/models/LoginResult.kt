package com.plcoding.socialnetworktwitch.feature_auth.domain.models

import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.feature_auth.presentation.util.AuthError

data class LoginResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
