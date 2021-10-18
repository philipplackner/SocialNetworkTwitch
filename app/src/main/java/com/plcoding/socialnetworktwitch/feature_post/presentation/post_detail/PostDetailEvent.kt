package com.plcoding.socialnetworktwitch.feature_post.presentation.post_detail

sealed class PostDetailEvent {
    object LikePost: PostDetailEvent()
    data class Comment(val comment: String): PostDetailEvent()
    data class LikeComment(val commentId: String): PostDetailEvent()
    object SharePost: PostDetailEvent()
}
