package com.alextsy.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.alextsy.storage.database.entity.CategoryEntity
import com.alextsy.storage.database.entity.CategoryWithSubcategories
import com.alextsy.storage.database.entity.SubcategoryEntity
import com.alextsy.storage.database.entity.TransactionEntity

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubcategory(subcategory: SubcategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSubcategory(subcategory: SubcategoryEntity)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate")
    suspend fun getTransactionsByDateRange(
        startDate: Long,
        endDate: Long,
    ): List<TransactionEntity>

    @Transaction
    @Query("SELECT * FROM categories")
    suspend fun getCategoriesWithSubcategories(): List<CategoryWithSubcategories>
}