package com.plcoding.socialnetworktwitch.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.socialnetworktwitch.presentation.activity.ActivityScreen
import com.plcoding.socialnetworktwitch.presentation.chat.ChatScreen
import com.plcoding.socialnetworktwitch.presentation.create_post.CreatePostScreen
import com.plcoding.socialnetworktwitch.presentation.main_feed.MainFeedScreen
import com.plcoding.socialnetworktwitch.presentation.login.LoginScreen
import com.plcoding.socialnetworktwitch.presentation.profile.ProfileScreen
import com.plcoding.socialnetworktwitch.presentation.register.RegisterScreen
import com.plcoding.socialnetworktwitch.presentation.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(navController = navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController = navController)
        }
        composable(Screen.ActivityScreen.route) {
            ActivityScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(navController = navController)
        }
    }
}