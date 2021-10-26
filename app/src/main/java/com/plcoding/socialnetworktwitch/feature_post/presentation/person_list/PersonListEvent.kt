package com.plcoding.socialnetworktwitch.feature_post.presentation.person_list

sealed class PersonListEvent {
    data class ToggleFollowStateForUser(val userId: String): PersonListEvent()
}
