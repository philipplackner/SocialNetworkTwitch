package com.plcoding.socialnetworktwitch.feature_profile.data.remote

import com.plcoding.socialnetworktwitch.core.data.dto.response.BasicApiResponse
import com.plcoding.socialnetworktwitch.feature_profile.data.remote.response.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("/api/user/profile")
    suspend fun getProfile(
        @Query("userId") userId: String
    ): BasicApiResponse<ProfileResponse>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}