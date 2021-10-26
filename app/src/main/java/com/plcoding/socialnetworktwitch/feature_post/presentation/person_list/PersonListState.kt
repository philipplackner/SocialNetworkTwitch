package com.plcoding.socialnetworktwitch.feature_post.presentation.person_list

import com.plcoding.socialnetworktwitch.core.domain.models.UserItem

data class PersonListState(
    val users: List<UserItem> = emptyList(),
    val isLoading: Boolean = false
)
