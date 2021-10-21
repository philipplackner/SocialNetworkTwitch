package com.plcoding.socialnetworktwitch.feature_post.presentation.util

import com.plcoding.socialnetworktwitch.core.util.Error

sealed class CommentError : Error() {
    object FieldEmpty: CommentError()
}
