package com.plcoding.socialnetworktwitch.feature_profile.presentation.edit_profile

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.domain.states.StandardTextFieldState
import com.plcoding.socialnetworktwitch.core.presentation.util.UiEvent
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.Skill
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import com.plcoding.socialnetworktwitch.feature_profile.domain.use_case.ProfileUseCases
import com.plcoding.socialnetworktwitch.feature_profile.presentation.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _usernameState = mutableStateOf(StandardTextFieldState())
    val usernameState: State<StandardTextFieldState> = _usernameState

    private val _githubTextFieldState = mutableStateOf(StandardTextFieldState())
    val githubTextFieldState: State<StandardTextFieldState> = _githubTextFieldState

    private val _instagramTextFieldState = mutableStateOf(StandardTextFieldState())
    val instagramTextFieldState: State<StandardTextFieldState> = _instagramTextFieldState

    private val _linkedInTextFieldState = mutableStateOf(StandardTextFieldState())
    val linkedInTextFieldState: State<StandardTextFieldState> = _linkedInTextFieldState

    private val _bioState = mutableStateOf(StandardTextFieldState())
    val bioState: State<StandardTextFieldState> = _bioState

    private val _skills = mutableStateOf(SkillsState())
    val skills: State<SkillsState> = _skills

    private val _profileState = mutableStateOf(ProfileState())
    val profileState: State<ProfileState> = _profileState

    private val _bannerUri = mutableStateOf<Uri?>(null)
    val bannerUri: State<Uri?> = _bannerUri

    private val _profilePictureUri = mutableStateOf<Uri?>(null)
    val profilePictureUri: State<Uri?> = _profilePictureUri

    init {
        savedStateHandle.get<String>("userId")?.let { userId ->
            getSkills()
            getProfile(userId)
        }
    }

    private fun getSkills() {
        viewModelScope.launch {
            val result = profileUseCases.getSkills()
            when (result) {
                is Resource.Success -> {
                    _skills.value = skills.value.copy(
                        skills = result.data ?: kotlin.run {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    UiText.StringResource(R.string.error_couldnt_load_skills)
                                )
                            )
                            return@launch
                        }
                    )
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError()))
                    return@launch
                }
            }
        }
    }

    private fun getProfile(userId: String) {
        viewModelScope.launch {
            _profileState.value = profileState.value.copy(isLoading = true)
            val result = profileUseCases.getProfile(userId)
            when (result) {
                is Resource.Success -> {
                    val profile = result.data ?: kotlin.run {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.error_couldnt_load_profile)
                            )
                        )
                        return@launch
                    }
                    _usernameState.value = usernameState.value.copy(
                        text = profile.username
                    )
                    _githubTextFieldState.value = _githubTextFieldState.value.copy(
                        text = profile.gitHubUrl ?: ""
                    )
                    _instagramTextFieldState.value = _instagramTextFieldState.value.copy(
                        text = profile.instagramUrl ?: ""
                    )
                    _linkedInTextFieldState.value = _linkedInTextFieldState.value.copy(
                        text = profile.linkedInUrl ?: ""
                    )
                    _bioState.value = bioState.value.copy(
                        text = profile.bio
                    )
                    _skills.value = _skills.value.copy(
                        selectedSkills = profile.topSkills
                    )
                    _profileState.value = profileState.value.copy(
                        profile = profile,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError()))
                    return@launch
                }
            }
        }
    }

    private fun updateProfile() {
        viewModelScope.launch {
            val result = profileUseCases.updateProfile(
                updateProfileData = UpdateProfileData(
                    username = usernameState.value.text,
                    bio = bioState.value.text,
                    gitHubUrl = githubTextFieldState.value.text,
                    instagramUrl = instagramTextFieldState.value.text,
                    linkedInUrl = linkedInTextFieldState.value.text,
                    skills = skills.value.selectedSkills
                ),
                profilePictureUri = profilePictureUri.value,
                bannerUri = bannerUri.value
            )
            when(result) {
                is Resource.Success -> {
                    _eventFlow.emit(UiEvent.ShowSnackbar(UiText.StringResource(R.string.updated_profile)))
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError()))
                }
            }
        }
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.EnteredUsername -> {
                _usernameState.value = usernameState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.EnteredGitHubUrl -> {
                _githubTextFieldState.value = _githubTextFieldState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.EnteredInstagramUrl -> {
                _instagramTextFieldState.value = _instagramTextFieldState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.EnteredLinkedInUrl -> {
                _linkedInTextFieldState.value = _linkedInTextFieldState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.EnteredBio -> {
                _bioState.value = _bioState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.CropProfilePicture -> {
                _profilePictureUri.value = event.uri
            }
            is EditProfileEvent.CropBannerImage -> {
                _bannerUri.value = event.uri
            }
            is EditProfileEvent.SetSkillSelected -> {

            }
            is EditProfileEvent.UpdateProfile -> {
                updateProfile()
            }
        }
    }
}