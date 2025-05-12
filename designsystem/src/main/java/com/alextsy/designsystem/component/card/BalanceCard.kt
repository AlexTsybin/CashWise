package com.alextsy.designsystem.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alextsy.designsystem.component.button.CwButton
import com.alextsy.designsystem.component.charts.ProgressBar
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType

@Composable
fun BalanceCard(
    balance: Double,
    progress: Float,
    onDetailClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                CwText(
                    text = "Balance",
                    textType = TextType.LABEL_LARGE,
                )
                CwText(
                    text = balance.formatAsCurrency(),
                    textType = TextType.DISPLAY_MEDIUM_INCOME,
                )
            }
            CwButton(
                text = "Details",
                isFullWidth = true,
            ) {
                onDetailClick()
            }
        }
    }
    ProgressBar(progress = progress)
}