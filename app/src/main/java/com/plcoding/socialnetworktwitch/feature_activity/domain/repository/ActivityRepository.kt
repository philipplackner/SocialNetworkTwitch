package com.plcoding.socialnetworktwitch.feature_activity.domain.repository

import androidx.paging.PagingData
import com.plcoding.socialnetworktwitch.core.domain.models.Activity
import com.plcoding.socialnetworktwitch.core.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    val activities: Flow<PagingData<Activity>>
}