package com.alextsy.designsystem.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.LocalShapes

@Composable
fun CwCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                shape = LocalShapes.current.button,
            )
            .padding(LocalDimensions.current.dimensions16),
    ) {
        content()
    }
}