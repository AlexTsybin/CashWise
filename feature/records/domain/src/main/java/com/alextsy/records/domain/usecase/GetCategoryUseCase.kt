package com.alextsy.records.domain.usecase

import com.alextsy.common.model.records.RecordTransactionType
import com.alextsy.records.domain.repository.UpdateFinanceRepository

class GetCategoryUseCase(
    private val repository: UpdateFinanceRepository
) {

    suspend operator fun invoke(isIncome: Boolean) =
        repository.getCategoriesWithSubcategories().filter {
            if (isIncome) {
                it.type == RecordTransactionType.CREDIT
            } else {
                it.type == RecordTransactionType.DEBIT
            }
        }
}