package com.alextsy.designsystem.component.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.LocalShapes

@Composable
private fun TabItem(
    text: String,
    modifier: Modifier,
    isSelected: Boolean,
    background: Color,
    onCLick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = background, shape = LocalShapes.current.button)
            .clickable { onCLick() }
            .padding(vertical = LocalDimensions.current.dimensions12),
        contentAlignment = Alignment.Center,
    ) {
        CwText(
            text = text,
            textType = if (isSelected) TextType.BUTTON_LABEL else TextType.BUTTON_INACTIVE,
        )
    }
}

@Composable
fun AnimatedTab(
    tabsList: Array<String>,
    onCLick: (Int) -> Unit,
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for ((index, tabText) in tabsList.withIndex()) {
                val isSelected = index == selectedItemIndex
                val background: Color by animateColorAsState(
                    targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing,
                    ),
                    label = "",
                )

                TabItem(
                    text = tabText,
                    modifier = Modifier.weight(0.5f),
                    isSelected = isSelected,
                    background = background,
                ) {
                    selectedItemIndex = index
                    onCLick.invoke(selectedItemIndex)
                }
            }
        }
    }
}

@CwPreview
@Composable
fun AnimatedTabPreview() {
    PreviewSurface {
        AnimatedTab(
            arrayOf("Income", "Expense"),
            onCLick = {},
        )
    }
}