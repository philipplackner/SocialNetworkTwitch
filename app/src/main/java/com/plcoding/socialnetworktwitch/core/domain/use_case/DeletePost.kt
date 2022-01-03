package com.plcoding.socialnetworktwitch.core.domain.use_case

import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository

class DeletePost(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): SimpleResource {
        return repository.deletePost(postId)
    }
}