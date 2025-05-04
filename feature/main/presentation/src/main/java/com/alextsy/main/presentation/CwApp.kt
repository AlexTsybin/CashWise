package com.alextsy.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alextsy.designsystem.component.container.ScreenContainer
import com.alextsy.designsystem.theme.CashWiseTheme
import com.alextsy.designsystem.utility.UiText
import com.alextsy.main.presentation.navigation.Graphs
import com.alextsy.main.presentation.navigation.navigateToOnboarding
import kotlinx.coroutines.delay

@Composable
fun CwApp() {
    val navController = rememberNavController()
    CashWiseTheme {
        var showErrorMessage by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf<UiText>(UiText.EmptyString) }

        LaunchedEffect(key1 = showErrorMessage) {
            if (showErrorMessage) {
                delay(3000)
                showErrorMessage = false
            }
        }

        ScreenContainer(showErrorMessage, errorMessage.toString()) {
            NavHost(
                navController = navController,
                startDestination = Graphs.Onboarding
            ) {
                navigateToOnboarding(navController) { showError, message ->
                    showErrorMessage = showError
                    errorMessage = message
                }
            }
        }
    }
}