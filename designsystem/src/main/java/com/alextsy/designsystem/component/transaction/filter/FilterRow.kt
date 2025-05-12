package com.alextsy.designsystem.component.transaction.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType

@Composable
fun FilterRow(onFilterSelected: (FilterType) -> Unit) {
    var selectedFilter by remember { mutableStateOf(FilterType.NONE) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        CwText(
            text = "Filter",
            textType = TextType.LABEL_SMALL,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        FilterButton(
            text = "Credit",
            isSelected = selectedFilter == FilterType.INCOME,
        ) {
            selectedFilter = if (selectedFilter == FilterType.INCOME) FilterType.NONE else FilterType.INCOME
            onFilterSelected(selectedFilter)
        }
        FilterButton(
            text = "Debit",
            isSelected = selectedFilter == FilterType.EXPENSE,
        ) {
            selectedFilter = if (selectedFilter == FilterType.EXPENSE) FilterType.NONE else FilterType.EXPENSE
            onFilterSelected(selectedFilter)
        }
    }
}