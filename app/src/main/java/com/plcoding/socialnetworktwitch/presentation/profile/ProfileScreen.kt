package com.plcoding.socialnetworktwitch.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.domain.models.Post
import com.plcoding.socialnetworktwitch.domain.models.User
import com.plcoding.socialnetworktwitch.presentation.components.Post
import com.plcoding.socialnetworktwitch.presentation.components.StandardToolbar
import com.plcoding.socialnetworktwitch.presentation.profile.components.BannerSection
import com.plcoding.socialnetworktwitch.presentation.profile.components.ProfileHeaderSection
import com.plcoding.socialnetworktwitch.presentation.ui.theme.ProfilePictureSizeLarge
import com.plcoding.socialnetworktwitch.presentation.ui.theme.SpaceMedium
import com.plcoding.socialnetworktwitch.presentation.util.Screen

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                BannerSection(
                    modifier = Modifier
                        .aspectRatio(2.5f)
                )
            }
            item {
                ProfileHeaderSection(
                    user = User(
                        profilePictureUrl = "",
                        username = "Philipp Lackner",
                        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                                "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                                "magna aliquyam erat, sed diam voluptua",
                        followerCount = 234,
                        followingCount = 534,
                        postCount = 65
                    )
                )
            }
            items(20) {
                Spacer(
                    modifier = Modifier
                        .height(SpaceMedium)
                        .offset(y = -ProfilePictureSizeLarge / 2f),
                    )
                Post(
                    post = Post(
                        username = "Philipp Lackner",
                        imageUrl = "",
                        profilePictureUrl = "",
                        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                                "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                                "magna aliquyam erat, sed diam voluptua...",
                        likeCount = 17,
                        commentCount = 7,
                    ),
                    showProfileImage = false,
                    onPostClick = {
                        navController.navigate(Screen.PostDetailScreen.route)
                    },
                    modifier = Modifier
                        .offset(y = -ProfilePictureSizeLarge / 2f),
                )
            }
        }
    }
}