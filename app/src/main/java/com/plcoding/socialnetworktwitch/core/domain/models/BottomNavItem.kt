package com.plcoding.socialnetworktwitch.core.domain.models

import androidx.compose.ui.graphics.vector.ImageVector
import com.plcoding.socialnetworktwitch.destinations.Destination

sealed interface BottomNavItem {
    val icon: ImageVector?
    val contentDescription: String?
    val alertCount: Int?
}

data class SpacerBottomNavItem(
    override val icon: ImageVector? = null,
    override val contentDescription: String? = null,
    override val alertCount: Int? = null,
) : BottomNavItem

data class DestinationBottomNavItem(
    val destination: Destination,
    override val icon: ImageVector? = null,
    override val contentDescription: String? = null,
    override val alertCount: Int? = null,
) : BottomNavItem
