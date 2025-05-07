package com.alextsy.designsystem.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions

enum class ButtonType {
    FILLED_TONAL,
    TEXT,
    ELEVATED,
}

fun ButtonType.getTextType() =
    when (this) {
    ButtonType.FILLED_TONAL -> TextType.BUTTON_LABEL
    ButtonType.TEXT -> TextType.LABEL_SMALL
    ButtonType.ELEVATED -> TextType.LABEL_LARGE
}

@Composable
fun ButtonType.getButtonElevation() =
    when (this) {
    ButtonType.FILLED_TONAL -> ButtonDefaults.filledTonalButtonElevation()
    ButtonType.TEXT -> ButtonDefaults.buttonElevation()
    ButtonType.ELEVATED -> ButtonDefaults.elevatedButtonElevation()
}

@Composable
fun ButtonType.getButtonColor() =
    when (this) {
    ButtonType.FILLED_TONAL -> ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    )
    ButtonType.TEXT -> ButtonDefaults.textButtonColors()
    ButtonType.ELEVATED -> ButtonDefaults.elevatedButtonColors()
}

@Composable
fun Modifier.buttonWidth(isFullWith: Boolean) =
    if (isFullWith) {
    fillMaxWidth()
} else {
    width(LocalDimensions.current.dimensions180)
}.height(LocalDimensions.current.dimensions56)