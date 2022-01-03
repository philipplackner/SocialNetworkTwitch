package com.plcoding.socialnetworktwitch.feature_post.presentation.main_feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.presentation.components.Post
import com.plcoding.socialnetworktwitch.core.presentation.components.StandardToolbar
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.plcoding.socialnetworktwitch.core.util.Screen
import com.plcoding.socialnetworktwitch.core.util.sendSharePostIntent
import com.plcoding.socialnetworktwitch.feature_post.presentation.person_list.PostEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun MainFeedScreen(
    imageLoader: ImageLoader,
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: MainFeedViewModel = hiltViewModel()
) {
    val pagingState = viewModel.pagingState.value
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PostEvent.OnLiked -> {

                }
            }
        }

    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            title = {
                Text(
                    text = stringResource(id = R.string.your_feed),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
            navActions = {
                IconButton(onClick = {
                    onNavigate(Screen.SearchScreen.route)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(pagingState.items.size) { i ->
                    val post = pagingState.items[i]
                    if (i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                        viewModel.loadNextPosts()
                    }
                    Post(
                        post = post,
                        imageLoader = imageLoader,
                        onUsernameClick = {
                            onNavigate(Screen.ProfileScreen.route + "?userId=${post.userId}")
                        },
                        onPostClick = {
                            onNavigate(Screen.PostDetailScreen.route + "/${post.id}")
                        },
                        onCommentClick = {
                            onNavigate(Screen.PostDetailScreen.route + "/${post.id}?shouldShowKeyboard=true")
                        },
                        onLikeClick = {
                            viewModel.onEvent(MainFeedEvent.LikedPost(post.id))
                        },
                        onShareClick = {
                            context.sendSharePostIntent(post.id)
                        },
                        onDeleteClick = {
                            viewModel.onEvent(MainFeedEvent.DeletePost(post))
                        }
                    )
                    if (i < pagingState.items.size - 1) {
                        Spacer(modifier = Modifier.height(SpaceLarge))
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
            if (pagingState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Center)
                )
            }
        }
    }
}