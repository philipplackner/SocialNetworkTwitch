package com.plcoding.socialnetworktwitch.feature_chat.data.remote.data

import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Message
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class WsServerMessage(
    val fromId: String,
    val toId: String,
    val text: String,
    val timestamp: Long,
    val chatId: String?,
) {
    fun toMessage(): Message {
        return Message(
            fromId = fromId,
            toId = toId,
            text = text,
            formattedTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                .format(timestamp),
            chatId = chatId,
        )
    }
}