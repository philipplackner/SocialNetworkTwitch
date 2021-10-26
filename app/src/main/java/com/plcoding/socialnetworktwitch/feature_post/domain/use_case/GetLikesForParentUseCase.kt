package com.plcoding.socialnetworktwitch.feature_post.domain.use_case

import com.plcoding.socialnetworktwitch.core.domain.models.UserItem
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository

class GetLikesForParentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(parentId: String): Resource<List<UserItem>> {
        return repository.getLikesForParent(parentId)
    }
}