package com.alextsy.designsystem.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alextsy.common.model.charts.CircleGraphData
import com.alextsy.common.model.formatAsCurrency
import com.alextsy.designsystem.component.charts.VennChart
import com.alextsy.designsystem.component.chip.CategoryChip
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.LocalWindowType
import com.alextsy.designsystem.theme.WindowType

@Composable
fun CircleIncomeCard(
    list: List<CircleGraphData>,
    month: String,
) {
    CwCard {
        when (LocalWindowType.current) {
            WindowType.Compact -> {
                CwText(
                    text = "$month Earning",
                    textType = TextType.TITLE,
                )
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
                VennChart(
                    factors = 2,
                    data = list,
                )
                LazyRow {
                    items(list.sortedByDescending { it.value }) {
                        CategoryChip(
                            text = it.valueLabel
                                .plus(" - ")
                                .plus(it.value.formatAsCurrency()),
                            iconColor = it.valueColor,
                        )
                    }
                }
            }
            else -> {
                CwText(
                    text = "$month Earning",
                    textType = TextType.TITLE,
                )
                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
                Row {
                    Column {
                        list.sortedByDescending { it.value }.forEach {
                            CategoryChip(
                                text = it.valueLabel
                                    .plus(" - ")
                                    .plus(it.value.formatAsCurrency()),
                                iconColor = it.valueColor,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
                    VennChart(
                        factors = 3,
                        data = list,
                    )
                }
            }
        }
    }
}