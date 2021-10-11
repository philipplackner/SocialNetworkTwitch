package com.plcoding.socialnetworktwitch.core.domain.models

data class UserItem(
    val userId: String,
    val username: String,
    val profilePictureUrl: String,
    val bio: String,
    val isFollowing: Boolean
)
