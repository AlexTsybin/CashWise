package com.alextsy.designsystem.component.container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.utility.UiText

@Composable
fun ScreenContainer(
    showBanner: Boolean = false,
    bannerMessage: UiText,
    isErrorBanner: Boolean = false,
    content: @Composable () -> Unit,
) {
    var showErrorMessage by remember { mutableStateOf(showBanner) }

    LaunchedEffect(key1 = showBanner) {
        showErrorMessage = showBanner
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        Box(
            modifier = Modifier.padding(LocalDimensions.current.dimensions16),
        ) {
            content()
        }

        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isErrorBanner) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primary)
                .padding(8.dp),
            visible = showErrorMessage,
            enter = slideInVertically(
                initialOffsetY = { -30 },
            ) + expandVertically(
                expandFrom = Alignment.Top,
            ) + fadeIn(initialAlpha = 0.3f),
        ) {
            val context = LocalContext.current
            Text(
                text = bannerMessage.getString(context),
                color = if (isErrorBanner) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}