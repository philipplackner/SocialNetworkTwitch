package com.plcoding.socialnetworktwitch.feature_chat.data.repository

import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.feature_chat.data.remote.ChatApi
import com.plcoding.socialnetworktwitch.feature_chat.data.remote.ChatService
import com.plcoding.socialnetworktwitch.feature_chat.data.remote.data.WsClientMessage
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Chat
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Message
import com.plcoding.socialnetworktwitch.feature_chat.domain.repository.ChatRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import retrofit2.HttpException
import java.io.IOException

class ChatRepositoryImpl(
    private val chatService: ChatService,
    private val chatApi: ChatApi
): ChatRepository {

    override suspend fun getChatsForUser(): Resource<List<Chat>> {
        return try {
            val chats = chatApi
                .getChatsForUser()
                .mapNotNull { it.toChat() }
            Resource.Success(data = chats)
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getMessagesForChat(chatId: String, page: Int, pageSize: Int): Resource<List<Message>> {
        return try {
            val messages = chatApi
                .getMessagesForChat(chatId, page, pageSize)
                .map { it.toMessage() }
            Resource.Success(data = messages)
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override fun observeChatEvents(): Flow<WebSocket.Event> {
        return chatService.observeEvents()
            .receiveAsFlow()
            .onEach {
                println("Received chat event: $it")
            }
    }

    override fun observeMessages(): Flow<Message> {
        return chatService
            .observeMessages()
            .receiveAsFlow()
            .onEach { msg ->
                println("Received message: $msg")
            }
            .map { it.toMessage() }
    }

    override fun sendMessage(toId: String, text: String, chatId: String?) {
        chatService.sendMessage(
            WsClientMessage(
                toId = toId,
                text = text,
                chatId = chatId
            )
        )
    }
}