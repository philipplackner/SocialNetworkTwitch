package com.plcoding.socialnetworktwitch.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.domain.models.User
import com.plcoding.socialnetworktwitch.presentation.ui.theme.SpaceLarge

@Composable
fun ProfileStats(
    user: User,
    modifier: Modifier = Modifier,
    isOwnProfile: Boolean = true,
    isFollowing: Boolean = true,
    onFollowClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileNumber(
            number = user.followerCount,
            text = stringResource(id = R.string.followers)
        )
        Spacer(modifier = Modifier.width(SpaceLarge))
        ProfileNumber(
            number = user.followingCount,
            text = stringResource(id = R.string.following)
        )
        Spacer(modifier = Modifier.width(SpaceLarge))
        ProfileNumber(
            number = user.postCount,
            text = stringResource(id = R.string.posts)
        )
        if(isOwnProfile) {
            Spacer(modifier = Modifier.width(SpaceLarge))
            Button(
                onClick = onFollowClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isFollowing) {
                        Color.Red
                    } else MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = if (isFollowing) {
                        stringResource(id = R.string.unfollow)
                    } else stringResource(id = R.string.follow),
                    color = if(isFollowing) {
                        Color.White
                    } else MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}