package com.alextsy.records.presentation.addrecords.mvi

import com.alextsy.common.model.records.Categorization

sealed class FinanceUiState {
    data object Initial : FinanceUiState()
    data class CategoriesLoaded(val categories: Categorization) : FinanceUiState()
}