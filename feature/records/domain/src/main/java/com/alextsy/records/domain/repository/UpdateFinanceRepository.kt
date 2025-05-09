package com.alextsy.records.domain.repository

import com.alextsy.common.model.records.Categorization
import com.alextsy.common.model.records.Transaction

interface UpdateFinanceRepository {

    suspend fun saveTransaction(transaction: Transaction)

    suspend fun getCategoriesWithSubcategories(): List<Categorization>
}