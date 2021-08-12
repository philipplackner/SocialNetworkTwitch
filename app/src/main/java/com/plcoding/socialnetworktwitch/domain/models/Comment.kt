package com.plcoding.socialnetworktwitch.domain.models

data class Comment(
    val commentId: Int = -1,
    val username: String = "",
    val profilePictureUrl: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val comment: String = "",
    val isLiked: Boolean = false,
    val likeCount: Int = 12
)
