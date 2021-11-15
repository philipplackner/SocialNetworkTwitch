package com.plcoding.socialnetworktwitch.feature_chat.presentation.message

import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Message

data class MessageState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
