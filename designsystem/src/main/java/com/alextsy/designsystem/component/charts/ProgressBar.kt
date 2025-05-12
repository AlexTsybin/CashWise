package com.alextsy.designsystem.component.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun ProgressBar(
    progress: Float,
    progressBackground: Color = MaterialTheme.colorScheme.inverseOnSurface,
    progressForeground: Color = MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalDimensions.current.dimensions12)
            .background(
                color = progressBackground,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(LocalDimensions.current.dimensions12)
                .background(
                    color = progressForeground,
                    shape = RoundedCornerShape(LocalDimensions.current.dimensions8)
                )
        )
    }
}