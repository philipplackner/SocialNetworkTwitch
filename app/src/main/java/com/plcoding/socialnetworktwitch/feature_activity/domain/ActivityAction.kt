package com.plcoding.socialnetworktwitch.feature_activity.domain

sealed class ActivityAction {
    object LikedPost : ActivityAction()
    object CommentedOnPost : ActivityAction()
    object FollowedYou : ActivityAction()
}
