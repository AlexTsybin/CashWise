package com.alextsy.records.data.repository

import com.alextsy.common.model.records.Categorization
import com.alextsy.common.model.records.Category
import com.alextsy.common.model.records.RecordTransactionType
import com.alextsy.common.model.records.Transaction
import com.alextsy.records.domain.repository.UpdateFinanceRepository
import com.alextsy.storage.database.dao.FinanceDao
import com.alextsy.storage.database.entity.TransactionEntity
import com.alextsy.storage.database.entity.TransactionType
import kotlinx.coroutines.delay

class UpdateFinanceRepositoryImpl(
    private val dao: FinanceDao,
) : UpdateFinanceRepository {

    override suspend fun saveTransaction(transaction: Transaction) {
        dao.insertTransaction(
            TransactionEntity(
                type = when (transaction.type) {
                    RecordTransactionType.CREDIT -> TransactionType.CREDIT
                    RecordTransactionType.DEBIT -> TransactionType.DEBIT
                },
                amount = transaction.amount,
                categoryId = transaction.categoryId,
                subcategoryId = transaction.subcategoryId,
                date = transaction.date,
                notes = transaction.notes
            )
        )
    }

    override suspend fun getCategoriesWithSubcategories(): List<Categorization> {
        var categories = dao.getCategoriesWithSubcategories()
        while (categories.isEmpty()) {
            delay(100)
            categories = dao.getCategoriesWithSubcategories()
        }
        return categories.map {
            Categorization(
                type = when (it.category.type) {
                    TransactionType.CREDIT -> RecordTransactionType.CREDIT
                    TransactionType.DEBIT -> RecordTransactionType.DEBIT
                },
                Category(
                    name = it.category.name,
                    icon = it.category.icon,
                    id = it.category.id
                ),
                it.subcategories.map { subcategory ->
                    Category(
                        name = subcategory.name,
                        icon = subcategory.icon,
                        id = subcategory.id
                    )
                }
            )
        }
    }
}