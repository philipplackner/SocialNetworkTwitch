package com.plcoding.socialnetworktwitch.feature_activity.presentation

import com.plcoding.socialnetworktwitch.core.domain.models.Activity

data class ActivityState(
    val activities: List<Activity> = emptyList(),
    val isLoading: Boolean = false,
)
