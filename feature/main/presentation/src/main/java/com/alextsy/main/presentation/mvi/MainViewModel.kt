package com.alextsy.main.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextsy.main.domain.UserPreferenceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    repository: UserPreferenceRepository
) : ViewModel() {

    val uiState: StateFlow<UiState> = repository.getUserPreference().map {
        UiState.Success(it)
    }.distinctUntilChanged().stateIn(
        scope = viewModelScope,
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}