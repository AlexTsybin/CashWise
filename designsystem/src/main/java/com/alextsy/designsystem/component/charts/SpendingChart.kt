package com.alextsy.designsystem.component.charts

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alextsy.common.model.charts.ProgressBarData
import com.alextsy.common.model.formatAsCurrency
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun SpendingItem(
    spending: ProgressBarData,
    topSpending: Float,
) {
    val targetProgress = (spending.value / topSpending).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "Progress Animation",
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CwText(
                text = spending.valueLabel,
                textType = TextType.LABEL_SMALL,
            )
            CwText(
                text = spending.value.formatAsCurrency(),
                textType = TextType.LABEL_LARGE,
            )
        }
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
        ProgressBar(
            progress = animatedProgress,
            progressForeground = Color(android.graphics.Color.parseColor(spending.valueColor)),
            progressBackground = MaterialTheme.colorScheme.surfaceContainer,
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions12))
    }
}