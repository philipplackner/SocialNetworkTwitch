package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

import com.plcoding.socialnetworktwitch.core.domain.use_case.ToggleFollowStateForUserUseCase

data class ProfileUseCases(
    val getProfile: GetProfileUseCase,
    val updateProfile: UpdateProfileUseCase,
    val getSkills: GetSkillsUseCase,
    val setSkillSelected: SetSkillSelectedUseCase,
    val getPostsForProfile: GetPostsForProfileUseCase,
    val searchUser: SearchUserUseCase,
    val toggleFollowStateForUser: ToggleFollowStateForUserUseCase,
    val logout: LogoutUseCase
)
