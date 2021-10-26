package com.plcoding.socialnetworktwitch.feature_post.presentation.person_list

import com.plcoding.socialnetworktwitch.core.presentation.util.UiEvent
import com.plcoding.socialnetworktwitch.core.util.Event

sealed class PostEvent : Event() {
    object OnLiked: PostEvent()
}