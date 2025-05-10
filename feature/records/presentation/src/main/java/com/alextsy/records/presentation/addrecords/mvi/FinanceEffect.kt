package com.alextsy.records.presentation.addrecords.mvi

import com.alextsy.common.model.records.Categorization
import com.alextsy.designsystem.utility.UiText

sealed class FinanceEffect {
    data class CategoriesUpdated(val categories: Categorization) : FinanceEffect()
    data class ShowSuccessBanner(val text: UiText) : FinanceEffect()
}