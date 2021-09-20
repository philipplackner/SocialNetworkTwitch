package com.plcoding.socialnetworktwitch.feature_post.domain.use_case

import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository

class GetPostsForFollowsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): Resource<List<Post>> {
        return repository.getPostsForFollows(page, pageSize)
    }
}