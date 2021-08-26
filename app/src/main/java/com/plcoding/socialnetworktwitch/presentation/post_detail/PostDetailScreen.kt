package com.plcoding.socialnetworktwitch.presentation.post_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.domain.models.Comment
import com.plcoding.socialnetworktwitch.domain.models.Post
import com.plcoding.socialnetworktwitch.presentation.components.ActionRow
import com.plcoding.socialnetworktwitch.presentation.components.StandardToolbar
import com.plcoding.socialnetworktwitch.presentation.ui.theme.*

@Composable
fun PostDetailScreen(
    navController: NavController,
    post: Post
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.your_feed),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                ) {
                    Spacer(modifier = Modifier.height(SpaceLarge))
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(y = ProfilePictureSizeMedium / 2f)
                                .clip(MaterialTheme.shapes.medium)
                                .shadow(5.dp)
                                .background(MediumGray)
                        ) {
                            Image(
                                painterResource(id = R.drawable.kermit),
                                contentDescription = "Post image",
                                modifier = Modifier.fillMaxWidth()
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(SpaceLarge)
                            ) {
                                ActionRow(
                                    username = "Philipp Lackner",
                                    modifier = Modifier.fillMaxWidth(),
                                    onLikeClick = { isLiked ->

                                    },
                                    onCommentClick = {

                                    },
                                    onShareClick = {

                                    },
                                    onUsernameClick = { username ->

                                    }
                                )
                                Spacer(modifier = Modifier.height(SpaceSmall))
                                Text(
                                    text = post.description,
                                    style = MaterialTheme.typography.body2,
                                )
                                Spacer(modifier = Modifier.height(SpaceMedium))
                                Text(
                                    text = stringResource(
                                        id = R.string.liked_by_x_people,
                                        post.likeCount
                                    ),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.body2
                                )
                            }

                        }
                        Image(
                            painterResource(id = R.drawable.philipp),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(ProfilePictureSizeMedium)
                                .clip(CircleShape)
                                .align(Alignment.TopCenter)
                        )

                    }
                }
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
            items(20) {
                Comment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceSmall
                        ),
                    comment = Comment(
                        username = "Philipp Lackner$it",
                        comment = "Lorem ipsum dolor sit amet, consetetur, asdfadsf\n" +
                                "diam nonumy eirmod tempor invidunt ut fda fdsa\n" +
                                "magna aliquyam erat, sed diam voluptua" +
                                "Lorem ipsum dolor sit amet, consetetur, asdfadsf\\n\" +\n" +
                                "                                \"diam nonumy eirmod tempor invidunt ut fda fdsa\\n\" +\n" +
                                "                                \"magna aliquyam erat, sed diam voluptua",
                    )
                )
            }
        }
    }
}

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comment: Comment = Comment(),
    onLikeClick: (Boolean) -> Unit = {}
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.onSurface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceMedium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.philipp),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(ProfilePictureSizeExtraSmall)
                    )
                    Spacer(modifier = Modifier.width(SpaceSmall))
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Text(
                    text = "2 days ago",
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = comment.comment,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.weight(9f)
                )
                Spacer(modifier = Modifier.width(SpaceMedium))
                IconButton(
                    onClick = {
                        onLikeClick(comment.isLiked)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = if (comment.isLiked) {
                            stringResource(id = R.string.unlike)
                        } else stringResource(id = R.string.like)
                    )
                }
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            Text(
                text = stringResource(id = R.string.liked_by_x_people, comment.likeCount),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}