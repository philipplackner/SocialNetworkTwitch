package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

import androidx.paging.PagingData
import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetPostsForProfileUseCase(
    private val repository: ProfileRepository
) {

    operator fun invoke(userId: String): Flow<PagingData<Post>> {
        return repository.getPostsPaged(userId)
    }
}