package com.alextsy.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

sealed interface WindowType {
    data object Compact : WindowType
    data object Medium : WindowType
    data object Expanded : WindowType
}

@Composable
fun rememberWindowSize(): WindowType {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 600 -> WindowType.Compact
        configuration.screenWidthDp < 840 -> WindowType.Medium
        else -> WindowType.Expanded
    }
}

val LocalWindowType = compositionLocalOf<WindowType> { error("WindowType not provided") }