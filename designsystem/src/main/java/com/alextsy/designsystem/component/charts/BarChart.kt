package com.alextsy.designsystem.component.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.graphExpense
import com.alextsy.designsystem.theme.graphIncome
import com.alextsy.common.model.charts.MonthlyData

@Composable
fun BarChart(
    selectedIndex: Int,
    listData: List<MonthlyData>,
    onClick: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    val maxValue = (listData.maxOfOrNull { maxOf(it.income, it.expense) } ?: 1) + 10

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            modifier = Modifier
                .padding(end = LocalDimensions.current.dimensions8)
        ) {
            (0..maxValue step maxValue / 4).reversed().forEach {
                Text(
                    text = "$it",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(bottom = LocalDimensions.current.dimensions24)
                )
            }
        }
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            listData.forEachIndexed { index, monthData ->
                BarItem(
                    monthData = monthData,
                    isSelected = index == selectedIndex,
                    maxValue = maxValue,
                    onClick = {
                        onClick(index)
                    }
                )
            }
        }
    }
}

@Composable
private fun BarItem(
    monthData: MonthlyData,
    isSelected: Boolean,
    maxValue: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = LocalDimensions.current.dimensions8)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(if (isSelected) Color.Gray.copy(alpha = 0.5f) else Color.Transparent)
                .padding(LocalDimensions.current.dimensions4)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .width(LocalDimensions.current.dimensions20)
                        .height(((monthData.income * 200.0 / maxValue).coerceAtLeast(1.0).toInt()).dp)
                        .background(
                            color = graphIncome,
                            shape = RoundedCornerShape(LocalDimensions.current.dimensions4)
                        )
                )
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions4))
                Box(
                    modifier = Modifier
                        .width(LocalDimensions.current.dimensions20)
                        .height((monthData.expense * 200.0 / maxValue).dp)
                        .background(
                            color = graphExpense,
                            shape = RoundedCornerShape(LocalDimensions.current.dimensions4)
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions4))
        CwText(
            text = monthData.label,
            textType = TextType.LABEL_SMALL
        )
    }
}