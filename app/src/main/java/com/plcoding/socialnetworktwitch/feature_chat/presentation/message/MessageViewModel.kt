package com.plcoding.socialnetworktwitch.feature_chat.presentation.message

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.plcoding.socialnetworktwitch.core.presentation.PagingState
import com.plcoding.socialnetworktwitch.core.presentation.util.UiEvent
import com.plcoding.socialnetworktwitch.core.util.DefaultPaginator
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.destinations.MessageScreenDestination
import com.plcoding.socialnetworktwitch.feature_chat.domain.model.Message
import com.plcoding.socialnetworktwitch.feature_chat.domain.use_case.ChatUseCases
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val chatUseCases: ChatUseCases,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _messageTextFieldState = mutableStateOf(StandardTextFieldState())
    val messageTextFieldState: State<StandardTextFieldState> = _messageTextFieldState

    private val _pagingState = mutableStateOf<PagingState<Message>>(PagingState())
    val pagingState: State<PagingState<Message>> = _pagingState

    private val _state = mutableStateOf(MessageState())
    val state: State<MessageState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val navArgs = MessageScreenDestination.argsFrom(savedStateHandle)

    private val _messageUpdatedEvent = MutableSharedFlow<MessageUpdateEvent>(replay = 1)
    val messageReceived = _messageUpdatedEvent.asSharedFlow()

    private val paginator = DefaultPaginator(
        onLoadUpdated = { isLoading ->
            _pagingState.value = pagingState.value.copy(isLoading = isLoading)
        },
        onRequest = { nextPage ->
            navArgs.chatId?.let { chatId ->
                chatUseCases.getMessagesForChat(
                    chatId, nextPage
                )
            } ?: Resource.Error(UiText.unknownError())
        },
        onError = { errorUiText ->
            _eventFlow.emit(UiEvent.ShowSnackbar(errorUiText))
        },
        onSuccess = { messages ->
            _pagingState.value = pagingState.value.copy(
                items = pagingState.value.items + messages,
                endReached = messages.isEmpty(),
                isLoading = false
            )
            viewModelScope.launch {
                _messageUpdatedEvent.emit(MessageUpdateEvent.MessagePageLoaded)
            }
        }
    )

    init {
        chatUseCases.initializeRepository()
        loadNextMessages()
        observeChatEvents()
        observeChatMessages()
    }

    private fun observeChatMessages() {
        viewModelScope.launch {
            chatUseCases.observeMessages()
                .collect { message ->
                    _pagingState.value = pagingState.value.copy(
                        items = pagingState.value.items + message
                    )
                    _messageUpdatedEvent.emit(MessageUpdateEvent.SingleMessageUpdate)
                }
        }
    }

    private fun observeChatEvents() {
        chatUseCases.observeChatEvents()
            .onEach { event ->
                when (event) {
                    is WebSocket.Event.OnConnectionOpened<*> -> {
                        println("Connection was opened")
                    }
                    is WebSocket.Event.OnConnectionFailed -> {
                        println("Connection failed: ${event.throwable}")
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun loadNextMessages() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private fun sendMessage() {
        val toId = navArgs.remoteUserId
        if (messageTextFieldState.value.text.isBlank()) {
            return
        }
        val chatId = navArgs.chatId
        chatUseCases.sendMessage(toId, messageTextFieldState.value.text, chatId)
        _messageTextFieldState.value = StandardTextFieldState()
        _state.value = state.value.copy(
            canSendMessage = false
        )
        viewModelScope.launch {
            _messageUpdatedEvent.emit(MessageUpdateEvent.MessageSent)
        }
    }

    fun onEvent(event: MessageEvent) {
        when (event) {
            is MessageEvent.EnteredMessage -> {
                _messageTextFieldState.value = messageTextFieldState.value.copy(
                    text = event.message
                )
                _state.value = state.value.copy(
                    canSendMessage = event.message.isNotBlank()
                )
            }
            is MessageEvent.SendMessage -> {
                sendMessage()

            }
        }
    }

    sealed class MessageUpdateEvent {
        object SingleMessageUpdate: MessageUpdateEvent()
        object MessagePageLoaded: MessageUpdateEvent()
        object MessageSent: MessageUpdateEvent()
    }
}