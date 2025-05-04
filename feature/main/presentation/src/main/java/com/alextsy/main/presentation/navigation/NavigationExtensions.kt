package com.alextsy.main.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alextsy.designsystem.utility.UiText
import com.alextsy.onboarding.presentation.intro.IntroductionScreen
import com.alextsy.onboarding.presentation.signin.SignInScreen
import com.alextsy.onboarding.presentation.welcome.WelcomeScreen

inline fun <reified T> NavGraphBuilder.cwComposable(
    noinline content: @Composable () -> Unit
) where T : Any, T : Destinations {
    composable<T>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(1000)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(1000)
            )
        }
    ) {
        content()
    }
}

fun NavGraphBuilder.navigateToOnboarding(
    navController: NavHostController,
    onError: (Boolean, UiText) -> Unit
) {
    navigation<Graphs.Onboarding>(
        startDestination = Destinations.Welcome
    ) {
        cwComposable<Destinations.Welcome> {
            WelcomeScreen(
                onNextScreen = { navController.navigate(Destinations.Introduction) }
            )
        }

        cwComposable<Destinations.Introduction> {
            IntroductionScreen(
                onNextScreen = { navController.navigate(Destinations.SignIn) }
            )
        }

        cwComposable<Destinations.SignIn> {
            SignInScreen(
                onNextScreen = { navController.navigate(Destinations.Home) },
                onError = onError
            )
        }
    }
}