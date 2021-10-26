package com.plcoding.socialnetworktwitch.feature_post.presentation.person_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.socialnetworktwitch.core.domain.use_case.GetOwnUserIdUseCase
import com.plcoding.socialnetworktwitch.core.presentation.util.UiEvent
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import com.plcoding.socialnetworktwitch.core.domain.use_case.ToggleFollowStateForUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val toggleFollowStateForUserUseCase: ToggleFollowStateForUserUseCase,
    private val getOwnUserId: GetOwnUserIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PersonListState())
    val state: State<PersonListState> = _state

    private val _ownUserId = mutableStateOf("")
    val ownUserId: State<String> = _ownUserId

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("parentId")?.let { parentId ->
            getLikesForParent(parentId)
            _ownUserId.value = getOwnUserId()
        }
    }

    fun onEvent(event: PersonListEvent) {
        when(event) {
            is PersonListEvent.ToggleFollowStateForUser -> {
                toggleFollowStateForUser(event.userId)
            }
        }
    }

    private fun toggleFollowStateForUser(userId: String) {
        viewModelScope.launch {
            val isFollowing = state.value.users.find {
                it.userId == userId
            }?.isFollowing == true

            _state.value = state.value.copy(
                users = state.value.users.map {
                    if(it.userId == userId) {
                        it.copy(isFollowing = !it.isFollowing)
                    } else it
                }
            )

            val result = toggleFollowStateForUserUseCase(
                userId = userId,
                isFollowing = isFollowing
            )
            when(result) {
                is Resource.Success -> Unit
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        users = state.value.users.map {
                            if(it.userId == userId) {
                                it.copy(isFollowing = isFollowing)
                            } else it
                        }
                    )
                    _eventFlow.emit(UiEvent.ShowSnackbar(
                        uiText = result.uiText ?: UiText.unknownError()
                    ))
                }
            }
        }
    }

    private fun getLikesForParent(parentId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = postUseCases.getLikesForParent(parentId)
            when(result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        users = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(isLoading = false)
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError())
                    )
                }
            }
        }
    }
}