package com.plcoding.socialnetworktwitch.feature_profile.domain.util

object ProfileConstants {

    const val MAX_SELECTED_SKILL_COUNT = 3

    val GITHUB_PROFILE_REGEX = "(https://)?(www\\.)?github\\.com/[A-z0-9_-]+/?".toRegex()
    val INSTAGRAM_PROFILE_REGEX = "(https?://)?(www\\.)?instagram\\.com/[a-z_\\-A-Z0-9]*".toRegex()
    val LINKED_IN_PROFILE_REGEX = "http(s)?://([\\w]+\\.)?linkedin\\.com/in/[A-z0-9_-]+/?".toRegex()
}