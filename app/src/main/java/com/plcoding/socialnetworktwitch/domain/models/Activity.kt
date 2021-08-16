package com.plcoding.socialnetworktwitch.domain.models

import com.plcoding.socialnetworktwitch.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)
