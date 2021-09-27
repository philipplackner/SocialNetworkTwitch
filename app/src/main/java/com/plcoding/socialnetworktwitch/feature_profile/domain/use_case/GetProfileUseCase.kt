package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Profile
import com.plcoding.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(userId: String): Resource<Profile> {
        return repository.getProfile(userId)
    }
}