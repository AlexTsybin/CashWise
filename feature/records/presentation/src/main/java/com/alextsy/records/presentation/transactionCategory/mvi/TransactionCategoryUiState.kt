package com.alextsy.records.presentation.transactionCategory.mvi

import com.alextsy.common.model.records.Categorization

sealed interface TransactionCategoryUiState {
    data object Initial : TransactionCategoryUiState
    data class CategoriesLoaded(val categories: List<Categorization>) : TransactionCategoryUiState
}