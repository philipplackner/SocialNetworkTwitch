package com.plcoding.socialnetworktwitch.core.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.core.domain.models.UserItem
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.feature_post.presentation.util.PostConstants
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Profile
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Skill
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getPostsPaged(
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE,
        userId: String
    ): Resource<List<Post>>

    suspend fun getProfile(userId: String): Resource<Profile>

    suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResource

    suspend fun getSkills(): Resource<List<Skill>>

    suspend fun searchUser(query: String): Resource<List<UserItem>>

    suspend fun followUser(userId: String): SimpleResource

    suspend fun unfollowUser(userId: String): SimpleResource

    fun logout()
}