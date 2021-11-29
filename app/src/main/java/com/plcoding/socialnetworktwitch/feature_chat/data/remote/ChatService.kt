package com.plcoding.socialnetworktwitch.feature_chat.data.remote

import com.plcoding.socialnetworktwitch.feature_chat.data.remote.data.WsClientMessage
import com.plcoding.socialnetworktwitch.feature_chat.data.remote.data.WsServerMessage
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface ChatService {

    @Receive
    fun observeEvents(): Flow<WebSocket.Event>

    @Send
    fun sendMessage(message: WsClientMessage)

    @Receive
    fun observeMessages(): Flow<WsServerMessage>
}