package com.plcoding.socialnetworktwitch.presentation.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plcoding.socialnetworktwitch.feature_auth.presentation.splash.SplashScreen
import com.plcoding.socialnetworktwitch.core.presentation.MainActivity
import com.plcoding.socialnetworktwitch.core.presentation.ui.theme.SocialNetworkTwitchTheme
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @RelaxedMockK
    lateinit var navController: NavController

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun splashScreen_displaysAndDisappears() = testDispatcher.runBlockingTest {
        composeTestRule.setContent {
            SocialNetworkTwitchTheme {
                SplashScreen(
                    destinationsNavigator = DestinationsNavController(navController, mockk(relaxed = true)),
                    dispatcher = testDispatcher
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Logo")
            .assertExists()

        advanceTimeBy(Constants.SPLASH_SCREEN_DURATION)

        verify {
            navController.popBackStack()
            navController.navigate(LoginScreenDestination.route)
        }
    }
}