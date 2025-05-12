package com.alextsy.designsystem.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alextsy.common.model.charts.MonthlyData
import com.alextsy.designsystem.component.charts.BarChart
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.LegendItem
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.graphExpense
import com.alextsy.designsystem.theme.graphIncome

@Composable
fun MonthlyBarCard(
    data: List<MonthlyData>,
    onClick: (String) -> Unit,
) {
    var selectedMonthIndex by remember(data) { mutableIntStateOf(data.size - 1) }

    CwCard {
        CwText(
            text = "Monthly",
            textType = TextType.TITLE,
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            LegendItem(label = "Income", color = graphIncome)
            LegendItem(label = "Expense", color = graphExpense)
        }
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
        BarChart(
            selectedIndex = selectedMonthIndex,
            listData = data,
        ) {
            selectedMonthIndex = it
            onClick(data[it].label)
        }
    }
}