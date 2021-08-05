package com.plcoding.socialnetworktwitch.presentation.main_feed

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.plcoding.socialnetworktwitch.presentation.components.Post

@Composable
fun MainFeedScreen(
    navController: NavController
) {
    Post(
        post = com.plcoding.socialnetworktwitch.domain.models.Post(
            username = "Philipp Lackner",
            imageUrl = "",
            profilePictureUrl = "",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                    "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                    "magna aliquyam erat, sed diam voluptua...",
            likeCount = 17,
            commentCount = 7
        )
    )
}