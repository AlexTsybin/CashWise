package com.alextsy.main.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alextsy.common.model.OnboardingConfig
import com.alextsy.common.model.records.Categorization
import com.alextsy.designsystem.utility.UiText
import com.alextsy.onboarding.presentation.intro.IntroductionScreen
import com.alextsy.onboarding.presentation.signin.SignInScreen
import com.alextsy.onboarding.presentation.welcome.WelcomeScreen
import com.alextsy.records.presentation.addrecords.screen.AddRecordScreen
import com.alextsy.records.presentation.transactionCategory.screen.SelectCategoryScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> NavGraphBuilder.cwComposable(
    noinline content: @Composable (NavBackStackEntry) -> Unit,
) where T : Any, T : Destinations {
    composable<T>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(1000),
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(1000),
            )
        },
    ) { backStackEntry ->
        content(backStackEntry)
    }
}

fun NavGraphBuilder.navigateToOnboarding(
    navController: NavHostController,
    onboardingConfig: OnboardingConfig,
    onError: (Boolean, UiText) -> Unit,
) {
    navigation<Graphs.Onboarding>(
        startDestination = getDestination(onboardingConfig),
    ) {
        cwComposable<Destinations.Welcome> {
            WelcomeScreen(
                onNextScreen = { navController.navigate(Destinations.Introduction) },
            )
        }

        cwComposable<Destinations.Introduction> {
            IntroductionScreen(
                onNextScreen = { navController.navigate(Destinations.SignIn) },
            )
        }

        cwComposable<Destinations.SignIn> {
            SignInScreen(
                onNextScreen = { navController.navigate(Destinations.AddRecords) },
                onError = onError,
            )
        }
    }
}

fun NavGraphBuilder.navigateToDashboard(
    navController: NavHostController,
    defaultCurrency: String,
    showBanner: (Boolean, UiText, Boolean) -> Unit,
) {
    navigation<Graphs.Dashboard>(
        startDestination = Destinations.AddRecords,
    ) {
        cwComposable<Destinations.SelectCategory> {
            val isIncome = it.arguments?.getBoolean("isIncome") ?: false
            SelectCategoryScreen(
                isIncome = isIncome,
                onCategorySelected = {
                    val json = Json.encodeToString<Categorization>(it)
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        key = "CATEGORIZATION_KEY",
                        value = json,
                    )
                    navController.popBackStack()
                },
            ) {
                navController.popBackStack()
            }
        }

        cwComposable<Destinations.AddRecords> {
            var expenseCategorization by remember { mutableStateOf<Categorization?>(null) }
            LaunchedEffect(navController) {
                navController.currentBackStackEntry?.savedStateHandle?.getStateFlow<String?>(
                    key = "CATEGORIZATION_KEY",
                    initialValue = null,
                )?.collect { json ->
                    json?.let {
                        expenseCategorization = Json.decodeFromString<Categorization>(it)
                    }
                }
            }

            AddRecordScreen(
                defaultCurrency = defaultCurrency,
                categorization = expenseCategorization,
                onSelectCategory = {
                    navController.navigate(Destinations.SelectCategory(it))
                },
                onCategoryChange = {
                    expenseCategorization = it
                },
                showBanner = showBanner,
            )
        }
    }
}

private fun getDestination(onboardingConfig: OnboardingConfig) =
    when (onboardingConfig) {
        OnboardingConfig.NOT_STARTED -> Destinations.Welcome
        OnboardingConfig.IN_PROGRESS -> Destinations.Introduction
        OnboardingConfig.COMPLETED -> Destinations.SignIn
        else -> Destinations.Welcome
    }