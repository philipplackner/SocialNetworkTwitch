package com.plcoding.socialnetworktwitch.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.domain.models.User
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.*

@Composable
fun ProfileHeaderSection(
    user: User,
    modifier: Modifier = Modifier,
    isOwnProfile: Boolean = true,
    onEditClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset(
                    x =
                    if (isOwnProfile) {
                        (30.dp + SpaceSmall) / 2f
                    } else 0.dp
                )
        ) {
            Text(
                text = user.username,
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center,
            )
            if (isOwnProfile) {
                Spacer(modifier = Modifier.width(SpaceSmall))
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(id = R.string.edit)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(SpaceMedium))
        if(user.description.isNotBlank()) {
            Text(
                text = user.description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
        }

        ProfileStats(user = user, isOwnProfile = isOwnProfile)
    }
}