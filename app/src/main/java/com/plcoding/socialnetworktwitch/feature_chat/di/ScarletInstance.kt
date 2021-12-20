package com.plcoding.socialnetworktwitch.feature_chat.di

import com.google.gson.Gson
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.feature_chat.data.remote.ChatApi
import com.plcoding.socialnetworktwitch.feature_chat.data.remote.ChatService
import com.plcoding.socialnetworktwitch.feature_chat.data.remote.util.CustomGsonMessageAdapter
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import okhttp3.OkHttpClient

object ScarletInstance {

    var current: ChatService? = null

    fun getNewInstance(client: OkHttpClient): ChatService {
        return Scarlet.Builder()
            .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(Gson()))
            .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
            .webSocketFactory(
                client.newWebSocketFactory("ws://192.168.0.3:8001/api/chat/websocket")
            )
            .backoffStrategy(LinearBackoffStrategy(Constants.RECONNECT_INTERVAL))
            .build()
            .create(ChatService::class.java)
            .also {
                current = it
            }
    }
}