package com.plcoding.socialnetworktwitch.feature_chat.data.remote

import com.plcoding.socialnetworktwitch.feature_chat.data.remote.data.ChatDto
import retrofit2.http.GET

interface ChatApi {

    @GET("/api/chats")
    suspend fun getChatsForUser(): List<ChatDto>

    companion object {
        const val BASE_URL = "http://192.168.0.2:8001/"
    }
}