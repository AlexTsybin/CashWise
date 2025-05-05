package com.alextsy.main.presentation.mvi

import com.alextsy.main.domain.UserPref

sealed interface UiState {

    data object Loading : UiState

    data class Success(val data: UserPref) : UiState
}