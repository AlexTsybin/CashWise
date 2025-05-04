package com.alextsy.main.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Graphs {
    @Serializable
    data object Onboarding : Graphs

    @Serializable
    data object Dashboard : Graphs
}