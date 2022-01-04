package com.plcoding.socialnetworktwitch.feature_chat.presentation.chat

import android.util.Base64
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.plcoding.socialnetworktwitch.destinations.MessageScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
@Destination
@Composable
fun ChatScreen(
    imageLoader: ImageLoader,
    navigator: DestinationsNavigator,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val chats = viewModel.state.value.chats
    val isLoading = viewModel.state.value.isLoading
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
                        navigator.navigate(
                            MessageScreenDestination(
                                remoteUserId = chat.remoteUserId,
                                remoteUsername = chat.remoteUsername,
                                encodedRemoteUserProfilePictureUrl = Base64.encodeToString(chat.remoteUserProfilePictureUrl.encodeToByteArray(), 0),
                                chatId = chat.chatId
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
        }
    }
}