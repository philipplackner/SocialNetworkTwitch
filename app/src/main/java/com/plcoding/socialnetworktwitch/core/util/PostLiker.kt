package com.plcoding.socialnetworktwitch.core.util

import com.plcoding.socialnetworktwitch.core.domain.models.Post

interface PostLiker {

    suspend fun toggleLike(
        posts: List<Post>,
        parentId: String,
        onRequest: suspend (isLiked: Boolean) -> SimpleResource,
        onStateUpdated: (List<Post>) -> Unit
    )
}