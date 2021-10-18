package com.plcoding.socialnetworktwitch.core.domain.models

data class Post(
    val id: String,
    val username: String,
    val imageUrl: String,
    val profilePictureUrl: String,
    val description: String,
    val likeCount: Int,
    val commentCount: Int
)
