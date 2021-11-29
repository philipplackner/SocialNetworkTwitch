package com.plcoding.socialnetworktwitch.feature_chat.domain.use_case

import com.plcoding.socialnetworktwitch.core.util.Constants.DEFAULT_PAGE_SIZE
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Chat
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Message
import com.plcoding.socialnetworktwitch.feature_chat.domain.repository.ChatRepository

class GetMessagesForChat(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(
        chatId: String,
        page: Int,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): Resource<List<Message>> {
        return repository.getMessagesForChat(
            chatId, page, pageSize
        )
    }
}