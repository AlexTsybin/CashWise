package com.alextsy.common.model.records

data class Categorization(
    val type: RecordTransactionType,
    val category: Category,
    val subcategories: List<Category>,
)