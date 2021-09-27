package com.plcoding.socialnetworktwitch.feature_profile.domain.repository

import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Profile

interface ProfileRepository {

    suspend fun getProfile(userId: String): Resource<Profile>
}