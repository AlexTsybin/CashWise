package com.alextsy.common.model.records

import kotlinx.serialization.Serializable

@Serializable
data class Categorization(
    val type: RecordTransactionType,
    val category: Category,
    val subcategories: List<Category>,
)