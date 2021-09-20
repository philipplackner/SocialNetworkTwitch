package com.plcoding.socialnetworktwitch.feature_auth.domain.repository

import com.plcoding.socialnetworktwitch.core.data.dto.response.BasicApiResponse
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.feature_auth.data.dto.request.CreateAccountRequest

interface AuthRepository {

    suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource

    suspend fun login(
        email: String,
        password: String
    ): SimpleResource
}