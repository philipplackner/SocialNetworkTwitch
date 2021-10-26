package com.plcoding.socialnetworktwitch.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.feature_post.presentation.person_list.PersonListScreen
import com.plcoding.socialnetworktwitch.feature_profile.presentation.edit_profile.EditProfileScreen
import com.plcoding.socialnetworktwitch.feature_activity.presentation.ActivityScreen
import com.plcoding.socialnetworktwitch.feature_chat.presentation.chat.ChatScreen
import com.plcoding.socialnetworktwitch.feature_post.presentation.create_post.CreatePostScreen
import com.plcoding.socialnetworktwitch.feature_post.presentation.main_feed.MainFeedScreen
import com.plcoding.socialnetworktwitch.feature_auth.presentation.login.LoginScreen
import com.plcoding.socialnetworktwitch.feature_post.presentation.post_detail.PostDetailScreen
import com.plcoding.socialnetworktwitch.feature_profile.presentation.profile.ProfileScreen
import com.plcoding.socialnetworktwitch.feature_auth.presentation.register.RegisterScreen
import com.plcoding.socialnetworktwitch.feature_profile.presentation.search.SearchScreen
import com.plcoding.socialnetworktwitch.feature_auth.presentation.splash.SplashScreen
import com.plcoding.socialnetworktwitch.core.util.Screen

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
            )
        }
        composable(Screen.ActivityScreen.route) {
            ActivityScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
            )
        }
        composable(
            route = Screen.ProfileScreen.route + "?userId={userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            println("Profile screen route: ${it.destination.route}")
            ProfileScreen(
                userId = it.arguments?.getString("userId"),
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
        composable(
            Screen.EditProfileScreen.route + "/{userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                }
            )
        ) {
            EditProfileScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
            )
        }
        composable(
            route = Screen.PostDetailScreen.route + "/{postId}",
            arguments = listOf(
                navArgument(
                    name = "postId"
                ) {
                    type = NavType.StringType
                }
            )
        ) {
            PostDetailScreen(
                scaffoldState = scaffoldState,
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate
            )
        }
        composable(
            route = Screen.PersonListScreen.route + "/{parentId}",
            arguments = listOf(
                navArgument("parentId") {
                    type = NavType.StringType
                }
            )
        ) {
            PersonListScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
    }
}