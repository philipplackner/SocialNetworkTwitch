package com.plcoding.socialnetworktwitch.feature_profile.domain.model

data class UserItem(
    val userId: String,
    val username: String,
    val profilePictureUrl: String,
    val bio: String,
    val isFollowing: Boolean
)
