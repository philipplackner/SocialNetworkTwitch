package com.plcoding.socialnetworktwitch.feature_post.data.remote.dto

import com.plcoding.socialnetworktwitch.core.domain.models.Comment
import java.text.SimpleDateFormat
import java.util.*

data class CommentDto(
    val id: String,
    val username: String,
    val profilePictureUrl: String,
    val timestamp: Long,
    val comment: String,
    val isLiked: Boolean,
    val likeCount: Int
) {
    fun toComment(): Comment {
        return Comment(
            id = id,
            username = username,
            profilePictureUrl = profilePictureUrl,
            formattedTime = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).run {
                format(timestamp)
            },
            comment = comment,
            isLiked = isLiked,
            likeCount = likeCount
        )
    }
}
