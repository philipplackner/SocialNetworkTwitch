package com.plcoding.socialnetworktwitch.core.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.socialnetworktwitch.R


@Composable
fun StandardToolbar(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {},
    showBackArrow: Boolean = false,
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit = {},
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = if(showBackArrow) {
            {
                IconButton(onClick = {
                    onNavigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        } else null,
        actions = navActions,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}