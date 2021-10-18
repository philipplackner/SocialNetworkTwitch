package com.plcoding.socialnetworktwitch.feature_post.presentation.post_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.socialnetworktwitch.core.presentation.util.UiEvent
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PostDetailState())
    val state: State<PostDetailState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("postId")?.let { postId ->
            loadPostDetails(postId)
            loadCommentsForPost(postId)
        }
    }

    fun onEvent(event: PostDetailEvent) {
        when(event) {
            is PostDetailEvent.LikePost -> {

            }
            is PostDetailEvent.Comment -> {

            }
            is PostDetailEvent.LikeComment -> {

            }
            is PostDetailEvent.SharePost -> {

            }
        }
    }

    private fun loadPostDetails(postId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoadingPost = true
            )
            val result = postUseCases.getPostDetails(postId)
            when(result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        post = result.data,
                        isLoadingPost = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoadingPost = false
                    )
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }

    private fun loadCommentsForPost(postId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoadingComments = true
            )
            val result = postUseCases.getCommentsForPost(postId)
            when(result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        comments = result.data ?: emptyList(),
                        isLoadingComments = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoadingComments = false
                    )
                }
            }
        }
    }
}