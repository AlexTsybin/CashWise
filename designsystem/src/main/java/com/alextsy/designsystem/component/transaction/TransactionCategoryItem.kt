package com.alextsy.designsystem.component.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.LocalShapes

@Composable
fun TransactionCategoryItem(icon: String) {
    Box(
        modifier = Modifier
            .size(LocalDimensions.current.dimensions48 * getScaleFactor())
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = LocalShapes.current.dots,
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = LocalShapes.current.dots,
            )
            .padding(LocalDimensions.current.dimensions8),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = icon)
    }
}