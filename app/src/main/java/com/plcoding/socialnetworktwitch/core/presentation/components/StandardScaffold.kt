package com.plcoding.socialnetworktwitch.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.domain.models.BottomNavItem
import com.plcoding.socialnetworktwitch.core.domain.models.DestinationBottomNavItem
import com.plcoding.socialnetworktwitch.core.domain.models.SpacerBottomNavItem
import com.plcoding.socialnetworktwitch.destinations.*
import com.plcoding.socialnetworktwitch.navDestination

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    state: ScaffoldState,
    bottomNavItems: List<BottomNavItem> = listOf(
        DestinationBottomNavItem(
            destination = MainFeedScreenDestination,
            icon = Icons.Outlined.Home,
            contentDescription = "Home"
        ),
        DestinationBottomNavItem(
            destination = ChatScreenDestination,
            icon = Icons.Outlined.Message,
            contentDescription = "Message"
        ),
        SpacerBottomNavItem(),
        DestinationBottomNavItem(
            destination = ActivityScreenDestination,
            icon = Icons.Outlined.Notifications,
            contentDescription = "Activity"
        ),
        DestinationBottomNavItem(
            destination = ProfileScreenDestination,
            icon = Icons.Outlined.Person,
            contentDescription = "Profile"
        ),
    ),
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.surface,
                    cutoutShape = CircleShape,
                    elevation = 5.dp
                ) {
                    BottomNavigation {
                        val currentDestination = navController.currentBackStackEntry?.navDestination
                        bottomNavItems.forEach { bottomNavItem ->
                            StandardBottomNavItem(
                                icon = bottomNavItem.icon,
                                contentDescription = bottomNavItem.contentDescription,
                                selected = bottomNavItem is DestinationBottomNavItem && currentDestination == bottomNavItem.destination,
                                alertCount = bottomNavItem.alertCount,
                                enabled = bottomNavItem.icon != null
                            ) {
                                if (bottomNavItem is DestinationBottomNavItem && currentDestination != bottomNavItem.destination) {
                                    navController.navigate(bottomNavItem.destination.route)
                                }
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = state,
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = onFabClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.make_post)
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) {
        content()
    }
}