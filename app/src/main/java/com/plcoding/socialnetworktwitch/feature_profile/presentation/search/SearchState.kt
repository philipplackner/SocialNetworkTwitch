package com.plcoding.socialnetworktwitch.feature_profile.presentation.search

import com.plcoding.socialnetworktwitch.core.domain.models.UserItem

data class SearchState(
    val userItems: List<UserItem> = emptyList(),
    val isLoading: Boolean = false
)
