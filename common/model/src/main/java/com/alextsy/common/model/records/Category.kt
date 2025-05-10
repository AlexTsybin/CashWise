package com.alextsy.common.model.records

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    val icon: String,
    val id: Int = 0,
)