package com.plcoding.socialnetworktwitch.feature_profile.presentation.util

import com.plcoding.socialnetworktwitch.core.util.Error

sealed class EditProfileError : Error() {
    object FieldEmpty: EditProfileError()
}
