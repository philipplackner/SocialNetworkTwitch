package com.plcoding.socialnetworktwitch.core.domain.models

import com.plcoding.socialnetworktwitch.feature_activity.domain.ActivityType

data class Activity(
    val userId: String,
    val parentId: String,
    val username: String,
    val activityType: ActivityType,
    val formattedTime: String,
)
