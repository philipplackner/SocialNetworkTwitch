package com.plcoding.socialnetworktwitch.feature_activity.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.core.domain.models.Activity
import com.plcoding.socialnetworktwitch.feature_activity.data.remote.ActivityApi
import retrofit2.HttpException
import java.io.IOException

class ActivitySource(
    private val api: ActivityApi
) : PagingSource<Int, Activity>() {

    private var currentPage = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Activity> {
        return try {
            val nextPage = params.key ?: currentPage
            val activities = api.getActivities(
                page = nextPage,
                pageSize = Constants.DEFAULT_PAGE_SIZE
            )
            LoadResult.Page(
                data = activities.map { it.toActivity() },
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = if (activities.isEmpty()) null else currentPage + 1
            ).also { currentPage++ }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Activity>): Int? {
        return state.anchorPosition
    }
}