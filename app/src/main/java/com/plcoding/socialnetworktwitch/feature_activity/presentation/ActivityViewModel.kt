package com.plcoding.socialnetworktwitch.feature_activity.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.plcoding.socialnetworktwitch.feature_activity.domain.repository.ActivityRepository
import com.plcoding.socialnetworktwitch.feature_activity.domain.use_case.GetActivitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val getActivities: GetActivitiesUseCase
) : ViewModel() {

    val activities = getActivities().cachedIn(viewModelScope)

    private val _state = mutableStateOf(ActivityState())
    val state: State<ActivityState> = _state

    fun onEvent(event: ActivityEvent) {
        when(event) {
            is ActivityEvent.ClickedOnUser -> {

            }
            is ActivityEvent.ClickedOnParent -> {

            }
        }
    }
}