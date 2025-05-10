package com.alextsy.records.presentation.addrecords.mvi

import com.alextsy.common.model.records.Transaction

sealed class FinanceEvent {
    data class SaveTransaction(val transaction: Transaction) : FinanceEvent()
    data class LoadCategories(val isIncome: Boolean) : FinanceEvent()
    data class UpdateCategories(val isIncome: Boolean) : FinanceEvent()
}