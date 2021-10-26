package com.plcoding.socialnetworktwitch.feature_profile.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.core.domain.models.User
import com.plcoding.socialnetworktwitch.core.presentation.components.Post
import com.plcoding.socialnetworktwitch.feature_profile.presentation.profile.components.BannerSection
import com.plcoding.socialnetworktwitch.feature_profile.presentation.profile.components.ProfileHeaderSection
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceSmall
import com.plcoding.socialnetworktwitch.core.presentation.util.UiEvent
import com.plcoding.socialnetworktwitch.core.presentation.util.asString
import com.plcoding.socialnetworktwitch.core.util.Screen
import com.plcoding.socialnetworktwitch.core.util.toPx
import com.plcoding.socialnetworktwitch.feature_post.presentation.person_list.PostEvent
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState,
    userId: String? = null,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    profilePictureSize: Dp = ProfilePictureSizeLarge,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val posts = viewModel.posts.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()
    val toolbarState = viewModel.toolbarState.value

    val iconHorizontalCenterLength =
        (LocalConfiguration.current.screenWidthDp.dp.toPx() / 4f -
                (profilePictureSize / 4f).toPx() -
                SpaceSmall.toPx()) / 2f
    val iconSizeExpanded = 35.dp
    val toolbarHeightCollapsed = 75.dp
    val imageCollapsedOffsetY = remember {
        (toolbarHeightCollapsed - profilePictureSize / 2f) / 2f
    }
    val iconCollapsedOffsetY = remember {
        (toolbarHeightCollapsed - iconSizeExpanded) / 2f
    }
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    val toolbarHeightExpanded = remember {
        bannerHeight + profilePictureSize
    }
    val maxOffset = remember {
        toolbarHeightExpanded - toolbarHeightCollapsed
    }

    val state = viewModel.state.value

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                if (delta > 0f && lazyListState.firstVisibleItemIndex != 0) {
                    return Offset.Zero
                }
                val newOffset = viewModel.toolbarState.value.toolbarOffsetY + delta
                viewModel.setToolbarOffsetY(
                    newOffset.coerceIn(
                        minimumValue = -maxOffset.toPx(),
                        maximumValue = 0f
                    )
                )
                viewModel.setExpandedRatio((viewModel.toolbarState.value.toolbarOffsetY + maxOffset.toPx()) / maxOffset.toPx())
                return Offset.Zero
            }
        }
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.setExpandedRatio(1f)
        viewModel.getProfile(userId)
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is PostEvent.OnLiked -> {
                    posts.refresh()
                }
            }
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState
        ) {
            item {
                Spacer(
                    modifier = Modifier.height(
                        toolbarHeightExpanded - profilePictureSize / 2f
                    )
                )
            }
            item {
                state.profile?.let { profile ->
                    ProfileHeaderSection(
                        user = User(
                            userId = profile.userId,
                            profilePictureUrl = profile.profilePictureUrl,
                            username = profile.username,
                            description = profile.bio,
                            followerCount = profile.followerCount,
                            followingCount = profile.followingCount,
                            postCount = profile.postCount
                        ),
                        isFollowing = profile.isFollowing,
                        isOwnProfile = profile.isOwnProfile,
                        onEditClick = {
                            onNavigate(Screen.EditProfileScreen.route + "/${profile.userId}")
                        }
                    )
                }
            }
            items(posts) { post ->
                Spacer(
                    modifier = Modifier
                        .height(SpaceMedium)
                )
                Post(
                    post = Post(
                        id = post?.id ?: "",
                        userId = post?.userId ?: "",
                        isLiked = post?.isLiked ?: false,
                        username = post?.username ?: "",
                        imageUrl = post?.imageUrl ?: "",
                        profilePictureUrl = post?.profilePictureUrl ?: "",
                        description = post?.description ?: "",
                        likeCount = post?.likeCount ?: 0,
                        commentCount = post?.commentCount ?: 0,
                    ),
                    showProfileImage = false,
                    onPostClick = {
                        onNavigate(Screen.PostDetailScreen.route + "/${post?.id}")
                    },
                    onLikeClick = {
                        viewModel.onEvent(ProfileEvent.LikePost(post?.id ?: ""))
                    }
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            state.profile?.let { profile ->
                BannerSection(
                    modifier = Modifier
                        .height(
                            (bannerHeight * toolbarState.expandedRatio).coerceIn(
                                minimumValue = toolbarHeightCollapsed,
                                maximumValue = bannerHeight
                            )
                        ),
                    leftIconModifier = Modifier
                        .graphicsLayer {
                            translationY = (1f - toolbarState.expandedRatio) *
                                    -iconCollapsedOffsetY.toPx()
                            translationX = (1f - toolbarState.expandedRatio) *
                                    iconHorizontalCenterLength
                        },
                    rightIconModifier = Modifier
                        .graphicsLayer {
                            translationY = (1f - toolbarState.expandedRatio) *
                                    -iconCollapsedOffsetY.toPx()
                            translationX = (1f - toolbarState.expandedRatio) *
                                    -iconHorizontalCenterLength
                        },
                    topSkills = profile.topSkills,
                    shouldShowGitHub = profile.gitHubUrl != null && profile.gitHubUrl.isNotBlank(),
                    shouldShowInstagram = profile.instagramUrl != null && profile.instagramUrl.isNotBlank(),
                    shouldShowLinkedIn = profile.linkedInUrl != null && profile.linkedInUrl.isNotBlank(),
                    bannerUrl = profile.bannerUrl
                )
                Image(
                    painter = rememberImagePainter(
                        data = profile.profilePictureUrl
                    ),
                    contentDescription = stringResource(id = R.string.profile_image),
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .graphicsLayer {
                            translationY = -profilePictureSize.toPx() / 2f -
                                    (1f - toolbarState.expandedRatio) * imageCollapsedOffsetY.toPx()
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0.5f,
                                pivotFractionY = 0f
                            )
                            val scale = 0.5f + toolbarState.expandedRatio * 0.5f
                            scaleX = scale
                            scaleY = scale
                        }
                        .size(profilePictureSize)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.onSurface,
                            shape = CircleShape
                        )
                )
            }
        }
    }


}