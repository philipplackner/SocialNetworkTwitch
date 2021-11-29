package com.plcoding.socialnetworktwitch.feature_chat.domain.use_case

import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Chat
import com.plcoding.socialnetworktwitch.feature_chat.domain.repository.ChatRepository

class GetChatsForUser(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(): Resource<List<Chat>> {
        return repository.getChatsForUser()
    }
}