package com.alextsy.designsystem.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alextsy.common.model.formatAsCurrency
import com.alextsy.designsystem.component.charts.LineChart
import com.alextsy.designsystem.component.ext.amountColor
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun FinanceCard(
    expenses: List<Float>,
    isIncome: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            CwText(
                text = if (isIncome) "Total Income" else "Total Expense",
                textType = TextType.TITLE,
            )
            CwText(
                text = "Last 12 Months",
                textType = TextType.LABEL_SMALL,
            )
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
            Text(
                text = expenses.sum().formatAsCurrency(),
                color = isIncome.amountColor(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        LineChart(
            expenses = expenses,
            color = isIncome.amountColor(),
        )
    }
}