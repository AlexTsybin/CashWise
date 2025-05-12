package com.alextsy.designsystem.component.ext

import androidx.compose.runtime.Composable
import com.alextsy.common.model.records.RecordTransactionType
import com.alextsy.designsystem.theme.graphExpense
import com.alextsy.designsystem.theme.graphIncome

@Composable
fun RecordTransactionType.getTextColor() =
    when (this) {
        RecordTransactionType.DEBIT -> graphExpense
        RecordTransactionType.CREDIT -> graphIncome
    }

@Composable
fun Boolean.amountColor() =
    if (this) {
        graphExpense
    } else {
        graphIncome
    }