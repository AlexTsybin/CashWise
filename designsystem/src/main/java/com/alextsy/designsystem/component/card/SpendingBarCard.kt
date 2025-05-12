package com.alextsy.designsystem.component.card

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alextsy.common.model.charts.ProgressBarData
import com.alextsy.designsystem.component.charts.SpendingItem
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun SpendingBarCard(
    spending: List<ProgressBarData>,
    month: String,
) {
    if (spending.isEmpty()) {
        return
    }

    val topSpendings = spending.sortedByDescending { it.value }
    val topSpending = spending.sortedByDescending { it.valueColor }.first().value

    CwCard {
        CwText(
            text = "$month Spending",
            textType = TextType.TITLE,
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
        topSpendings.forEach { spending ->
            SpendingItem(
                spending = spending,
                topSpending = topSpending,
            )
        }
    }
}