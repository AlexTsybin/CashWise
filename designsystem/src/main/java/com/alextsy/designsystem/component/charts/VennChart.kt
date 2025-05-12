package com.alextsy.designsystem.component.charts

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alextsy.common.model.charts.CircleGraphData
import com.alextsy.designsystem.theme.LocalDimensions
import kotlinx.coroutines.launch

@Composable
fun VennChart(
    factors: Int,
    data: List<CircleGraphData>,
) {
    val animateProgress = remember { androidx.compose.animation.core.Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(data) {
        animateProgress.snapTo(targetValue = 0f)
        coroutineScope.launch {
            animateProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(1000)
            )
        }
    }

    val indicatorBackground = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenWidthDp.dp / factors

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(screenHeight)
            .size(LocalDimensions.current.dimensions142)
            .drawBehind {
                drawPieIndicator(
                    list = data,
                    screenWidth = screenWidth,
                    indicatorBackground = indicatorBackground,
                    animationProgress = animateProgress.value
                )
            }
    ) {  }
}

private fun DrawScope.drawIndicator(
    color: Color,
    arcWidth: Double,
    startAngle: Float,
    sweepAngle: Float,
    currentRadius: Float,
    centerX: Float,
    centerY: Float,
) {
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = Offset(centerX - currentRadius, centerY - currentRadius),
        size = Size(currentRadius * 2, currentRadius * 2),
        style = Stroke(width = arcWidth.toFloat(), cap = StrokeCap.Round),
        alpha = 1.0f
    )
}

private fun DrawScope.drawPieIndicator(
    list: List<CircleGraphData>,
    screenWidth: Dp,
    indicatorBackground: Color,
    animationProgress: Float,
) {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val currentRadius = screenWidth.value / 4

    val maxValue = list.maxOfOrNull { it.value } ?: return
        list.sortedBy { it.value }.forEach { chartEntry ->
            val entryValue = chartEntry.value
            val sweepAngle = (SWEEP_ANGLE * entryValue / maxValue) * animationProgress

            drawIndicator(
                color = indicatorBackground,
                arcWidth = ARC_WIDTH,
                startAngle = -90f,
                sweepAngle = SWEEP_ANGLE,
                currentRadius = currentRadius,
                centerX = centerX,
                centerY = centerY
            )
            drawIndicator(
                color = Color(android.graphics.Color.parseColor(chartEntry.valueColor)),
                arcWidth = ARC_WIDTH,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                currentRadius = currentRadius,
                centerX = centerX,
                centerY = centerY
            )
            currentRadius += RADIUS_INCREMENT_PER_CIRCLE * RADIUS_INCREMENT_RATIO
        }
}