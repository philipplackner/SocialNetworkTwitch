package com.plcoding.socialnetworktwitch.feature_activity.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.plcoding.socialnetworktwitch.core.domain.models.Activity
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.feature_activity.data.paging.ActivitySource
import com.plcoding.socialnetworktwitch.feature_activity.data.remote.ActivityApi
import com.plcoding.socialnetworktwitch.feature_activity.domain.repository.ActivityRepository
import com.plcoding.socialnetworktwitch.feature_post.data.paging.PostSource
import kotlinx.coroutines.flow.Flow

class ActivityRepositoryImpl(
    private val api: ActivityApi
): ActivityRepository {

    override val activities: Flow<PagingData<Activity>>
        get() = Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
            ActivitySource(api)
        }.flow
}