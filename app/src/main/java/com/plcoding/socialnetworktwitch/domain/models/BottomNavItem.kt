package com.plcoding.socialnetworktwitch.domain.models

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: ImageVector? = null,
    val contentDescription: String? = null,
    val alertCount: Int? = null,
)
