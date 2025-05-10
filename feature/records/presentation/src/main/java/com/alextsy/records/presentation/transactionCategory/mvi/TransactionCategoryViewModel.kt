package com.alextsy.records.presentation.transactionCategory.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextsy.records.domain.usecase.GetCategoryUseCase
import com.alextsy.records.presentation.stateInWhileActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionCategoryViewModel(
    private val useCase: GetCategoryUseCase,
) : ViewModel() {

    private val mutableUiState: MutableStateFlow<TransactionCategoryUiState> = MutableStateFlow(TransactionCategoryUiState.Initial)
    val state: StateFlow<TransactionCategoryUiState>
        get() = mutableUiState
            .stateInWhileActive(
                viewModelScope,
                TransactionCategoryUiState.Initial,
            ) {}

    fun processEvent(event: TransactionCategoryEvent) {
        when (event) {
            is TransactionCategoryEvent.Initialize -> viewModelScope.launch {
                val categories = useCase(event.isIncome)
                mutableUiState.value = TransactionCategoryUiState.CategoriesLoaded(categories)
            }
        }
    }
}