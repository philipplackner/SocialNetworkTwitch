package com.plcoding.socialnetworktwitch.feature_chat.presentation.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.presentation.components.SendTextField
import com.plcoding.socialnetworktwitch.core.presentation.components.StandardToolbar
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.DarkerGreen
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Chat
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Message
import com.plcoding.socialnetworktwitch.feature_chat.presentation.message.components.OwnMessage
import com.plcoding.socialnetworktwitch.feature_chat.presentation.message.components.RemoteMessage

@ExperimentalCoilApi
@Composable
fun MessageScreen(
    chatId: String,
    imageLoader: ImageLoader,
    onNavigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: MessageViewModel = hiltViewModel()
) {
    val messages = remember {
        listOf(
            Message(
                fromId = "",
                toId = "",
                text = "Hello World!",
                formattedTime = "19:34",
                chatId = "",
                id = ""
            ),
            Message(
                fromId = "",
                toId = "",
                text = "Hello World!",
                formattedTime = "19:34",
                chatId = "",
                id = ""
            ),
            Message(
                fromId = "",
                toId = "",
                text = "Hello World!",
                formattedTime = "19:34",
                chatId = "",
                id = ""
            ),
        )
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            showBackArrow = true,
            title = {
                Image(
                    painter = rememberImagePainter(
                        data = "http://192.168.0.2:8001/profile_pictures/2d9d19dd-eb6b-4e51-957d-1381c4a28024.jpg",
                        imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(ProfilePictureSizeSmall)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(SpaceMedium))
                Text(
                    text = "Philipp",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(SpaceMedium)
            ) {
                items(messages) { message ->
                    RemoteMessage(
                        message = message.text,
                        formattedTime = message.formattedTime,
                        color = MaterialTheme.colors.surface,
                        textColor = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(SpaceMedium))
                    OwnMessage(
                        message = message.text,
                        formattedTime = message.formattedTime,
                        color = DarkerGreen,
                        textColor = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(SpaceMedium))
                }
            }
            SendTextField(
                state = viewModel.messageTextFieldState.value,
                onValueChange = {
                    viewModel.onEvent(MessageEvent.EnteredMessage(it))
                },
                onSend = {
                    viewModel.onEvent(MessageEvent.SendMessage)
                },
                hint = stringResource(id = R.string.enter_a_message)
            )
        }
    }
}