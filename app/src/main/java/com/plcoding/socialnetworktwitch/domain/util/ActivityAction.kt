package com.plcoding.socialnetworktwitch.domain.util

sealed class ActivityAction {
    object LikedPost : ActivityAction()
    object CommentedOnPost : ActivityAction()
}
