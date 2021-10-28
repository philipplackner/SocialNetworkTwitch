package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

import androidx.paging.PagingData
import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.core.domain.repository.ProfileRepository
import com.plcoding.socialnetworktwitch.core.util.Resource
import kotlinx.coroutines.flow.Flow

class GetPostsForProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(userId: String, page: Int): Resource<List<Post>> {
        return repository.getPostsPaged(
            userId = userId,
            page = page
        )
    }
}