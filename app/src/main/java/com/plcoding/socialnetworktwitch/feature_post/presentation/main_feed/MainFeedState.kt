package com.plcoding.socialnetworktwitch.feature_post.presentation.main_feed

import com.plcoding.socialnetworktwitch.core.domain.models.Post

data class MainFeedState(
    val isLoadingFirstTime: Boolean = true,
    val isLoadingNewPosts: Boolean = false,
)
