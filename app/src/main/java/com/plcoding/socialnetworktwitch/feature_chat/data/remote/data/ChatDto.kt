package com.plcoding.socialnetworktwitch.feature_chat.data.remote.data

import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Chat

data class ChatDto(
    val chatId: String,
    val remoteUserId: String?,
    val remoteUsername: String?,
    val remoteUserProfilePictureUrl: String?,
    val lastMessage: String?,
    val timestamp: Long?
) {
    fun toChat(): Chat? {
        return Chat(
            chatId = chatId,
            remoteUserId = remoteUserId ?: return null,
            remoteUsername = remoteUsername ?: return null,
            remoteUserProfilePictureUrl = remoteUserProfilePictureUrl ?: return null,
            lastMessage = lastMessage ?: return null,
            timestamp = timestamp ?: return null
        )
    }
}
