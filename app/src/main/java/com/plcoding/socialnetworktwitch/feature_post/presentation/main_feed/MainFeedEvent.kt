package com.plcoding.socialnetworktwitch.feature_post.presentation.main_feed

sealed class MainFeedEvent {

    object LoadMorePosts: MainFeedEvent()
    object LoadedPage: MainFeedEvent()

    data class LikedPost(val postId: String): MainFeedEvent()
}
