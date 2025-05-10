package com.alextsy.records.presentation.transactionCategory.mvi

sealed interface TransactionCategoryEvent {
    data class Initialize(val isIncome: Boolean) : TransactionCategoryEvent
}