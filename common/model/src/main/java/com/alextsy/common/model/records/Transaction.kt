package com.alextsy.common.model.records

data class Transaction(
    val type: RecordTransactionType,
    val amount: Double,
    val categoryId: Int,
    val subcategoryId: Int,
    val date: Long,
    val notes: String,
)
