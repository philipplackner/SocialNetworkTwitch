package com.plcoding.socialnetworktwitch.feature_profile.domain.use_case

import android.net.Uri
import android.util.Patterns
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.feature_profile.domain.model.UpdateProfileData
import com.plcoding.socialnetworktwitch.feature_profile.domain.repository.ProfileRepository

class UpdateProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(
        updateProfileData: UpdateProfileData,
        profilePictureUri: Uri?,
        bannerUri: Uri?
    ): SimpleResource {
        if (updateProfileData.username.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_username_empty)
            )
        }
        val isValidGithubUrl =
            Patterns.WEB_URL.matcher(updateProfileData.gitHubUrl).matches() &&
                    (updateProfileData.gitHubUrl.startsWith("https://github.com") ||
                    updateProfileData.gitHubUrl.startsWith("http://github.com") ||
                    updateProfileData.gitHubUrl.startsWith("github.com"))
        if (!isValidGithubUrl) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_invalid_github_url)
            )
        }
        return repository.updateProfile(
            updateProfileData = updateProfileData,
            profilePictureUri = profilePictureUri,
            bannerImageUri = bannerUri
        )
    }
}