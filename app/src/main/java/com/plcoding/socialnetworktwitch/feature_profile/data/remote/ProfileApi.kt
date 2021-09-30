package com.plcoding.socialnetworktwitch.feature_profile.data.remote

import com.plcoding.socialnetworktwitch.core.data.dto.response.BasicApiResponse
import com.plcoding.socialnetworktwitch.feature_profile.data.remote.response.ProfileResponse
import com.plcoding.socialnetworktwitch.feature_profile.data.remote.response.SkillDto
import okhttp3.MultipartBody
import retrofit2.http.*

interface ProfileApi {

    @GET("/api/user/profile")
    suspend fun getProfile(
        @Query("userId") userId: String
    ): BasicApiResponse<ProfileResponse>

    @Multipart
    @PUT("/api/user/update")
    suspend fun updateProfile(
        @Part bannerImage: MultipartBody.Part?,
        @Part profilePicture: MultipartBody.Part?,
        @Part updateProfileData: MultipartBody.Part
    ): BasicApiResponse<Unit>

    @GET("/api/skills/get")
    suspend fun getSkills(): List<SkillDto>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}