package com.plcoding.socialnetworktwitch.feature_post.presentation.main_feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.presentation.components.Post
import com.plcoding.socialnetworktwitch.core.presentation.components.StandardToolbar
import com.plcoding.socialnetworktwitch.core.util.Screen
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun MainFeedScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    scaffoldState: ScaffoldState,
    viewModel: MainFeedViewModel = hiltViewModel()
) {
    val posts = viewModel.posts.collectAsLazyPagingItems()
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
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
            if(state.isLoadingFirstTime) {
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }
            LazyColumn {
                items(posts) { post ->
                    Post(
                        post = com.plcoding.socialnetworktwitch.core.domain.models.Post(
                            id = post?.id ?: "",
                            username = post?.username ?: "",
                            imageUrl = post?.imageUrl ?: "",
                            profilePictureUrl = post?.profilePictureUrl ?: "",
                            description = post?.description ?: "",
                            likeCount = post?.likeCount ?: 0,
                            commentCount = post?.commentCount ?: 0
                        ),
                        onPostClick = {
                            onNavigate(Screen.PostDetailScreen.route + "/${post?.id}")
                        }
                    )
                }
                item {
                    if(state.isLoadingNewPosts) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
                posts.apply {
                    when {
                        loadState.refresh !is LoadState.Loading -> {
                            viewModel.onEvent(MainFeedEvent.LoadedPage)
                        }
                        loadState.append is LoadState.Loading -> {
                            viewModel.onEvent(MainFeedEvent.LoadMorePosts)
                        }
                        loadState.append is LoadState.NotLoading -> {
                            viewModel.onEvent(MainFeedEvent.LoadedPage)
                        }
                        loadState.append is LoadState.Error -> {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Error"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}