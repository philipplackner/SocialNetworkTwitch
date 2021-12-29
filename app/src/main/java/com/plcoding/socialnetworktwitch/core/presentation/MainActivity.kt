package com.plcoding.socialnetworktwitch.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.plcoding.socialnetworktwitch.core.presentation.components.Navigation
import com.plcoding.socialnetworktwitch.core.presentation.components.StandardScaffold
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SocialNetworkTwitchTheme
import com.plcoding.socialnetworktwitch.destinations.*
import com.plcoding.socialnetworktwitch.navDestination
import com.ramcosta.composedestinations.navigation.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SocialNetworkTwitchTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val scaffoldState = rememberScaffoldState()

                    StandardScaffold(
                        navController = navController,
                        showBottomBar = shouldShowBottomBar(navBackStackEntry),
                        state = scaffoldState,
                        modifier = Modifier.fillMaxSize(),
                        onFabClick = {
                            navController.navigateTo(CreatePostScreenDestination)
                        }
                    ) {
                        Navigation(navController, scaffoldState, imageLoader)
                    }
                }
            }
        }
    }

    private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        val currentDestination = backStackEntry?.navDestination
        val doesRouteMatch = currentDestination in listOf(
            MainFeedScreenDestination,
            ChatScreenDestination,
            ActivityScreenDestination
        )
        val isOwnProfile = currentDestination == ProfileScreenDestination &&
                ProfileScreenDestination.argsFrom(backStackEntry).userId == null
        return doesRouteMatch || isOwnProfile
    }
}
