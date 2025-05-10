package com.alextsy.main.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.ThemeConfig
import com.alextsy.designsystem.component.container.ScreenContainer
import com.alextsy.designsystem.theme.CashWiseTheme
import com.alextsy.designsystem.utility.UiText
import com.alextsy.main.presentation.mvi.UiState
import com.alextsy.main.presentation.navigation.Graphs
import com.alextsy.main.presentation.navigation.navigateToDashboard
import com.alextsy.main.presentation.navigation.navigateToOnboarding
import kotlinx.coroutines.delay

@Composable
fun CwApp(uiState: UiState.Success) {
    val navController = rememberNavController()
    CashWiseTheme(
        darkTheme = isDarkTheme(uiState),
    ) {
        var showBanner by remember { mutableStateOf(false) }
        var bannerMessage by remember { mutableStateOf<UiText>(UiText.EmptyString) }

        LaunchedEffect(key1 = showBanner) {
            if (showBanner) {
                delay(3000)
                showBanner = false
            }
        }

        ScreenContainer(
            showBanner = showBanner,
            bannerMessage = bannerMessage,
        ) {
            NavHost(
                navController = navController,
                startDestination = getDestination(uiState.data.authType),
            ) {
                navigateToOnboarding(
                    navController,
                    onboardingConfig = uiState.data.onboardingStatus,
                ) { isShowBanner, message ->
                    showBanner = isShowBanner
                    bannerMessage = message
                }
                navigateToDashboard(
                    navController = navController,
                    defaultCurrency = "$",
                ) { isShowBanner, message, _ ->
                    showBanner = isShowBanner
                    bannerMessage = message
                }
            }
        }
    }
}

private fun getDestination(authConfig: AuthConfig) =
    when (authConfig) {
        AuthConfig.AUTHENTICATED -> Graphs.Dashboard
        else -> Graphs.Onboarding
    }

@Composable
private fun isDarkTheme(uiState: UiState): Boolean {
    return when (uiState) {
        UiState.Loading -> false
        is UiState.Success -> when (uiState.data.theme) {
            ThemeConfig.SYSTEM -> isSystemInDarkTheme()
            ThemeConfig.LIGHT -> false
            ThemeConfig.DARK -> true
        }
    }
}