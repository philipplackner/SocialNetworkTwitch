package com.plcoding.socialnetworktwitch.feature_profile.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.plcoding.socialnetworktwitch.core.presentation.util.UiEvent
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.feature_profile.domain.use_case.ProfileUseCases
import com.plcoding.socialnetworktwitch.feature_profile.domain.util.ProfileConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val _searchFieldState = mutableStateOf(StandardTextFieldState())
    val searchFieldState: State<StandardTextFieldState> = _searchFieldState

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.Query -> {
                searchUser(event.query)
            }
            is SearchEvent.ToggleFollowState -> {
                toggleFollowStateForUser(event.userId)
            }
        }
    }

    private fun toggleFollowStateForUser(userId: String) {
        viewModelScope.launch {
            val isFollowing = searchState.value.userItems.find {
                it.userId == userId
            }?.isFollowing == true

            _searchState.value = searchState.value.copy(
                userItems = searchState.value.userItems.map {
                    if(it.userId == userId) {
                        it.copy(isFollowing = !it.isFollowing)
                    } else it
                }
            )

            val result = profileUseCases.toggleFollowStateForUser(
                userId = userId,
                isFollowing = isFollowing
            )
            when(result) {
                is Resource.Success -> Unit
                is Resource.Error -> {
                    _searchState.value = searchState.value.copy(
                        userItems = searchState.value.userItems.map {
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

    private fun searchUser(query: String) {
        _searchFieldState.value = searchFieldState.value.copy(
            text = query
        )
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(ProfileConstants.SEARCH_DELAY)
            _searchState.value = searchState.value.copy(
                isLoading = true
            )
            val result = profileUseCases.searchUser(query)
            when(result) {
                is Resource.Success -> {
                    _searchState.value = searchState.value.copy(
                        userItems = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _searchFieldState.value = searchFieldState.value.copy(
                        error = SearchError(
                            message = result.uiText ?: UiText.unknownError()
                        )
                    )
                    _searchState.value = searchState.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}