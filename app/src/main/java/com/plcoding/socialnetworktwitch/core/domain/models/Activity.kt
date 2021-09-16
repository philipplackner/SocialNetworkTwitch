package com.plcoding.socialnetworktwitch.core.domain.models

import com.plcoding.socialnetworktwitch.feature_activity.domain.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)
