package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

data class ProfileUseCases(
    val getProfile: GetProfileUseCase,
    val updateProfile: UpdateProfileUseCase,
    val getSkills: GetSkillsUseCase,
    val setSkillSelected: SetSkillSelectedUseCase
)
