package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Skill
import com.plcoding.socialnetworktwitch.core.domain.repository.ProfileRepository

class GetSkillsUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(): Resource<List<Skill>> {
        return repository.getSkills()
    }
}