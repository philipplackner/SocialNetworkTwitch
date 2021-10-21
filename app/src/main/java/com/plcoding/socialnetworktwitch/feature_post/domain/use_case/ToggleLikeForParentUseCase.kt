package com.plcoding.socialnetworktwitch.feature_post.domain.use_case

import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository
import com.plcoding.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository

class ToggleLikeForParentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        parentId: String,
        parentType: Int,
        isLiked: Boolean
    ): SimpleResource {
        return if(isLiked) {
            repository.unlikeParent(parentId, parentType)
        } else {
            repository.likeParent(parentId, parentType)
        }
    }
}