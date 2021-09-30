package com.plcoding.socialnetworktwitch.feature_profile.presentation.edit_profile

import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Skill

data class SkillsState(
    val skills: List<Skill> = emptyList(),
    val selectedSkills: List<Skill> = emptyList()
)
