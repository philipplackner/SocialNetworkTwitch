package com.plcoding.socialnetworktwitch.feature_post.presentation.util

import com.plcoding.socialnetworktwitch.core.util.Error

sealed class PostDescriptionError : Error() {
    object FieldEmpty: PostDescriptionError()
}
