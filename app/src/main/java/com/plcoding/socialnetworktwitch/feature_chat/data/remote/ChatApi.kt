package com.plcoding.socialnetworktwitch.feature_chat.data.remote

import com.plcoding.socialnetworktwitch.feature_chat.data.remote.data.ChatDto
import com.plcoding.socialnetworktwitch.feature_chat.data.remote.data.MessageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ChatApi {

    @GET("/api/chats")
    suspend fun getChatsForUser(): List<ChatDto>

    @GET("/api/chat/messages")
    suspend fun getMessagesForChat(
        @Query("chatId") chatId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<MessageDto>

    companion object {
        const val BASE_URL = "http://192.168.0.2:8001/"
    }
}