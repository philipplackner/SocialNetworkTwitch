package com.plcoding.socialnetworktwitch.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.plcoding.socialnetworktwitch.NavGraphs
import com.plcoding.socialnetworktwitch.destinations.*
import com.plcoding.socialnetworktwitch.feature_auth.presentation.login.LoginScreen
import com.plcoding.socialnetworktwitch.feature_auth.presentation.register.RegisterScreen
import com.plcoding.socialnetworktwitch.feature_chat.presentation.chat.ChatScreen
import com.plcoding.socialnetworktwitch.feature_chat.presentation.message.MessageScreen
import com.plcoding.socialnetworktwitch.feature_post.presentation.create_post.CreatePostScreen
import com.plcoding.socialnetworktwitch.feature_post.presentation.main_feed.MainFeedScreen
import com.plcoding.socialnetworktwitch.feature_post.presentation.person_list.PersonListScreen
import com.plcoding.socialnetworktwitch.feature_post.presentation.post_detail.PostDetailScreen
import com.plcoding.socialnetworktwitch.feature_profile.presentation.edit_profile.EditProfileScreen
import com.plcoding.socialnetworktwitch.feature_profile.presentation.profile.ProfileScreen
import com.plcoding.socialnetworktwitch.feature_profile.presentation.search.SearchScreen
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    imageLoader: ImageLoader
) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        navController = navController,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(LoginScreenDestination) {
            LoginScreen(
                navigator = destinationsNavigator,
                scaffoldState = scaffoldState
            )
        }

        composable(RegisterScreenDestination) {
            RegisterScreen(
                navigator = destinationsNavigator,
                scaffoldState = scaffoldState,
            )
        }

        composable(MainFeedScreenDestination) {
            MainFeedScreen(
                imageLoader = imageLoader,
                scaffoldState = scaffoldState,
                navigator = destinationsNavigator,
            )
        }

        composable(ChatScreenDestination) {
            ChatScreen(
                imageLoader = imageLoader,
                navigator = destinationsNavigator,
            )
        }

        composable(MessageScreenDestination) {
            MessageScreen(
                navArgs = navArgs,
                destinationsNavigator = destinationsNavigator,
                imageLoader = imageLoader
            )
        }

        composable(ProfileScreenDestination) {
            ProfileScreen(
                userId = navArgs.userId,
                navigator = destinationsNavigator,
                scaffoldState = scaffoldState,
                imageLoader = imageLoader
            )
        }

        composable(EditProfileScreenDestination) {
            EditProfileScreen(
                scaffoldState = scaffoldState,
                imageLoader = imageLoader,
                navigator = destinationsNavigator
            )
        }

        composable(CreatePostScreenDestination) {
            CreatePostScreen(
                navigator = destinationsNavigator,
                scaffoldState = scaffoldState,
                imageLoader = imageLoader
            )
        }

        composable(SearchScreenDestination) {
            SearchScreen(
                navigator = destinationsNavigator,
                imageLoader = imageLoader
            )
        }

        composable(PostDetailScreenDestination) {
            PostDetailScreen(
                scaffoldState = scaffoldState,
                imageLoader = imageLoader,
                navigator = destinationsNavigator,
                navArgs = navArgs,
            )
        }

        composable(PersonListScreenDestination) {
            PersonListScreen(
                scaffoldState = scaffoldState,
                imageLoader = imageLoader,
                navigator = destinationsNavigator
            )
        }
    }
}