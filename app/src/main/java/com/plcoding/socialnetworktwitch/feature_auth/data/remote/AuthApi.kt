package com.plcoding.socialnetworktwitch.feature_auth.data.remote

import com.plcoding.socialnetworktwitch.core.data.dto.response.BasicApiResponse
import com.plcoding.socialnetworktwitch.feature_auth.data.dto.request.CreateAccountRequest
import com.plcoding.socialnetworktwitch.feature_auth.data.dto.request.LoginRequest
import com.plcoding.socialnetworktwitch.feature_auth.data.dto.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/user/create")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>

    @POST("/api/user/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }
}