package com.plcoding.socialnetworktwitch.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object MainFeedScreen : Screen("main_feed_screen")
    object PostDetailScreen : Screen("post_detail_screen")
    object ChatScreen : Screen("chat_screen")
    object MessagesScreen : Screen("messages_screen")
    object ProfileScreen : Screen("profile_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
    object PersonListScreen : Screen("person_list_screen")
    object CreatePostScreen : Screen("create_post_screen")
    object ActivityScreen : Screen("activity_screen")
    object SearchScreen : Screen("search_screen")
}
