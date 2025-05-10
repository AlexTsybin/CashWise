package com.alextsy.main.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destinations {
    @Serializable
    data object Welcome : Destinations

    @Serializable
    data object Introduction : Destinations

    @Serializable
    data object SignIn : Destinations

    @Serializable
    data object Home : Destinations

    @Serializable
    data object AddRecords : Destinations

    @Serializable
    data class SelectCategory(val isIncome: Boolean) : Destinations
}