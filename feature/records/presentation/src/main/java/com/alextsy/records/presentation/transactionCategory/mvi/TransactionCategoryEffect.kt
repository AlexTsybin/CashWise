package com.alextsy.records.presentation.transactionCategory.mvi

import com.alextsy.common.model.records.Categorization

sealed interface TransactionCategoryEffect {
    data class NavigateBack(val category: Categorization) : TransactionCategoryEffect
}