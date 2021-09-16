package com.plcoding.socialnetworktwitch.feature_auth.presentation.util

import com.plcoding.socialnetworktwitch.core.util.Error

sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail: AuthError()
    object InvalidPassword : AuthError()
}
