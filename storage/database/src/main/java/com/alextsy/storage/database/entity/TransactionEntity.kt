package com.alextsy.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: TransactionType,
    val amount: Double,
    val categoryId: Int,
    val subcategoryId: Int,
    val date: Long,
    val notes: String,
)