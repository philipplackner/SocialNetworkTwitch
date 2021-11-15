package com.plcoding.socialnetworktwitch.feature_chat.presentation.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.plcoding.socialnetworktwitch.core.util.Screen
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Chat

@OptIn(ExperimentalCoilApi::class)
@ExperimentalMaterialApi
@Composable
fun ChatScreen(
    imageLoader: ImageLoader,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
) {
    val chats = remember {
        listOf(
            Chat(
                remoteUsername = "Philipp Lackner",
                remoteUserProfileUrl = "http://192.168.0.2:8001/profile_pictures/philipp.jpg",
                lastMessage = "This is the last message of the chat with Philipp",
                lastMessageFormattedTime = "19:39"
            ),
            Chat(
                remoteUsername = "Florian",
                remoteUserProfileUrl = "http://192.168.0.2:8001/profile_pictures/philipp.jpg",
                lastMessage = "This is the last message of the chat with Philipp",
                lastMessageFormattedTime = "19:39"
            ),
            Chat(
                remoteUsername = "Philipp Lackner",
                remoteUserProfileUrl = "http://192.168.0.2:8001/profile_pictures/philipp.jpg",
                lastMessage = "This is the last message of the chat with Philipp",
                lastMessageFormattedTime = "19:39"
            ),
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceMedium),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(chats) { chat ->
                ChatItem(
                    item = chat,
                    imageLoader = imageLoader,
                    onItemClick = {
                        onNavigate(Screen.MessageScreen.route)
                    }
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
        }
    }
}