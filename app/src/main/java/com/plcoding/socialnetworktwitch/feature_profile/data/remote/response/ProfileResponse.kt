package com.plcoding.socialnetworktwitch.feature_profile.data.remote.response

import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Profile

data class ProfileResponse(
    val userId: String,
    val username: String,
    val bio: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val profilePictureUrl: String,
    val bannerUrl: String,
    val topSkillUrls: List<String>,
    val gitHubUrl: String?,
    val instagramUrl: String?,
    val linkedInUrl: String?,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
) {
    fun toProfile(): Profile {
        return Profile(
            userId = userId,
            username = username,
            bio = bio,
            followingCount = followingCount,
            followerCount = followerCount,
            postCount = postCount,
            profilePictureUrl = profilePictureUrl,
            bannerUrl = bannerUrl,
            topSkillUrls = topSkillUrls,
            gitHubUrl = gitHubUrl,
            instagramUrl = instagramUrl,
            linkedInUrl = linkedInUrl,
            isOwnProfile = isOwnProfile,
            isFollowing = isFollowing
        )
    }
}
