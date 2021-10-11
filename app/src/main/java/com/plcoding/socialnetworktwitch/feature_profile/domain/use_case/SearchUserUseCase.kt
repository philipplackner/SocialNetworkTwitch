package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

import com.plcoding.socialnetworktwitch.core.domain.models.UserItem
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository

class SearchUserUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(query: String): Resource<List<UserItem>> {
        if(query.isBlank()) {
            return Resource.Success(data = emptyList())
        }
        return repository.searchUser(query)
    }
}