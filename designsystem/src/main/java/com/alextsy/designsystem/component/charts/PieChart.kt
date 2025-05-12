package com.alextsy.designsystem.component.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.alextsy.common.model.charts.PieData
import com.alextsy.common.model.formatAsCurrency
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType

@Composable
fun PieChart(
    expenses: List<PieData>,
    totalExpense: Double,
    factor: Float = 1f,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .size(LocalConfiguration.current.screenWidthDp.dp / factor),
        ) {
            val sweepAngles = expenses.map {
                (it.amount / totalExpense * 360).toFloat()
            }
            val gapAngle = 12f
            var startAngle = -90f
            expenses.forEachIndexed { index, expense ->
                drawArc(
                    color = Color(android.graphics.Color.parseColor(expense.color)),
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[index] - gapAngle,
                    useCenter = false,
                    style = Stroke(
                        width = 12.dp.toPx(),
                        cap = StrokeCap.Round,
                    ),
                    size = Size(size.width, size.height),
                    topLeft = Offset(0f, 0f),
                )
                startAngle += sweepAngles[index]
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CwText(
                text = "Expense",
                textType = TextType.LABEL_LARGE,
            )
            CwText(
                text = totalExpense.formatAsCurrency(),
                textType = TextType.TITLE,
            )
        }
    }
}