package com.alextsy.designsystem.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.alextsy.designsystem.theme.graphExpense
import com.alextsy.designsystem.theme.graphIncome

enum class TextType {
    DISPLAY_SMALL_GRADIENT,
    DISPLAY_MEDIUM,
    HEADLINE_SMALL,
    TITLE,
    LABEL_SMALL,
    LABEL_LARGE,
    BUTTON_LABEL,
    BUTTON_INACTIVE,
    LABEL_LARGE_PRIMARY,
    DISPLAY_MEDIUM_INCOME,
    DISPLAY_MEDIUM_EXPENSE,
}

@Composable
fun TextType.getColor() =
    when (this) {
        TextType.LABEL_SMALL -> MaterialTheme.colorScheme.onSurfaceVariant
        TextType.LABEL_LARGE -> MaterialTheme.colorScheme.onSurface
        TextType.TITLE -> MaterialTheme.colorScheme.onSurface
        TextType.HEADLINE_SMALL -> MaterialTheme.colorScheme.primary
        TextType.DISPLAY_SMALL_GRADIENT -> MaterialTheme.colorScheme.onSurface
        TextType.BUTTON_LABEL -> MaterialTheme.colorScheme.onPrimary
        TextType.DISPLAY_MEDIUM -> MaterialTheme.colorScheme.onSurface
        TextType.BUTTON_INACTIVE -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        TextType.LABEL_LARGE_PRIMARY -> MaterialTheme.colorScheme.primary
        TextType.DISPLAY_MEDIUM_INCOME -> graphIncome
        TextType.DISPLAY_MEDIUM_EXPENSE -> graphExpense
    }

@Composable
fun TextType.getStyle() =
    when (this) {
        TextType.LABEL_SMALL -> MaterialTheme.typography.labelSmall
        TextType.LABEL_LARGE -> MaterialTheme.typography.labelLarge
        TextType.TITLE -> MaterialTheme.typography.titleMedium
        TextType.HEADLINE_SMALL -> MaterialTheme.typography.headlineSmall
        TextType.DISPLAY_SMALL_GRADIENT -> MaterialTheme.typography.displaySmall.copy(
            brush = Brush.linearGradient(
                listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary),
            ),
        )
        TextType.BUTTON_LABEL -> MaterialTheme.typography.labelLarge
        TextType.BUTTON_INACTIVE -> MaterialTheme.typography.labelLarge
        TextType.LABEL_LARGE_PRIMARY -> MaterialTheme.typography.labelLarge
        TextType.DISPLAY_MEDIUM_INCOME,
        TextType.DISPLAY_MEDIUM_EXPENSE,
        TextType.DISPLAY_MEDIUM,
        -> MaterialTheme.typography.displayMedium
    }