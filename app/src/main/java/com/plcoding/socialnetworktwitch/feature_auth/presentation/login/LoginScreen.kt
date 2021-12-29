package com.plcoding.socialnetworktwitch.feature_auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.presentation.components.StandardTextField
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceLarge
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SpaceMedium
import com.plcoding.socialnetworktwitch.core.presentation.util.UiEvent
import com.plcoding.socialnetworktwitch.core.presentation.util.asString
import com.plcoding.socialnetworktwitch.destinations.LoginScreenDestination
import com.plcoding.socialnetworktwitch.destinations.MainFeedScreenDestination
import com.plcoding.socialnetworktwitch.destinations.RegisterScreenDestination
import com.plcoding.socialnetworktwitch.feature_auth.presentation.util.AuthError
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@Destination
@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val state = viewModel.loginState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is UiEvent.Navigate -> {
                    navigator.navigate(event.direction)
                }
                is UiEvent.OnLogin -> {
                    navigator.popBackStack(
                        direction = LoginScreenDestination,
                        inclusive = true
                    )
                    navigator.navigate(MainFeedScreenDestination)
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = SpaceLarge,
                bottom = 50.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = emailState.text,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                },
                keyboardType = KeyboardType.Email,
                error = when (emailState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                hint = stringResource(id = R.string.login_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = passwordState.text,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                hint = stringResource(id = R.string.password_hint),
                keyboardType = KeyboardType.Password,
                error = when (passwordState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                isPasswordVisible = state.isPasswordVisible,
                onPasswordToggleClick = {
                    viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = {
                    viewModel.onEvent(LoginEvent.Login)
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = MaterialTheme.colors.onPrimary
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            }
        }
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_an_account_yet))
                append(" ")
                val signUpText = stringResource(id = R.string.sign_up)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append(signUpText)
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    navigator.navigate(RegisterScreenDestination)
                }
        )
    }

}
