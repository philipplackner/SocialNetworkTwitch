package com.plcoding.socialnetworktwitch.feature_profile.presentation.search

import com.plcoding.socialnetworktwitch.core.util.Error
import com.plcoding.socialnetworktwitch.core.util.UiText

data class SearchError(
    val message: UiText
): Error()
