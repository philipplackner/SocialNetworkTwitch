package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository

class ToggleFollowStateForUserUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(userId: String, isFollowing: Boolean): SimpleResource {
        return if(isFollowing) {
            repository.unfollowUser(userId)
        } else {
            repository.followUser(userId)
        }
    }
}