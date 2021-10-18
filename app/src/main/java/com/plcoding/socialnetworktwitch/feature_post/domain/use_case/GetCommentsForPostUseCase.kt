package com.plcoding.socialnetworktwitch.feature_post.domain.use_case

import com.plcoding.socialnetworktwitch.core.domain.models.Comment
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository

class GetCommentsForPostUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): Resource<List<Comment>> {
        return repository.getCommentsForPost(postId)
    }
}