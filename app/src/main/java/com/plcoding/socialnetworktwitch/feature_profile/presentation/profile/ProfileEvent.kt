package com.plcoding.socialnetworktwitch.feature_profile.presentation.profile

sealed class ProfileEvent {
    data class GetProfile(val userId: String): ProfileEvent()
}
