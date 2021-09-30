package com.plcoding.socialnetworktwitch.feature_profile.domain.repository

import android.net.Uri
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Profile
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Skill
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData

interface ProfileRepository {

    suspend fun getProfile(userId: String): Resource<Profile>

    suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResource

    suspend fun getSkills(): Resource<List<Skill>>
}