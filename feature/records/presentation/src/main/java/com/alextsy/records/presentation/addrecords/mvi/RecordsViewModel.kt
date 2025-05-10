package com.alextsy.records.presentation.addrecords.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextsy.common.model.records.Transaction
import com.alextsy.designsystem.utility.UiText
import com.alextsy.records.domain.repository.UpdateFinanceRepository
import com.alextsy.records.domain.usecase.GetDefaultCategoryUseCase
import com.alextsy.records.presentation.stateInWhileActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RecordsViewModel(
    private val repository: UpdateFinanceRepository,
    private val useCase: GetDefaultCategoryUseCase,
) : ViewModel() {

    private val mutableSharedFlow: MutableSharedFlow<FinanceEffect> = MutableSharedFlow()
    val effect: SharedFlow<FinanceEffect>
        get() = mutableSharedFlow.asSharedFlow()

    private val mutableUiState: MutableStateFlow<FinanceUiState> = MutableStateFlow(FinanceUiState.Initial)
    val state: StateFlow<FinanceUiState>
        get() = mutableUiState
            .stateInWhileActive(viewModelScope, FinanceUiState.Initial) {
                processEvent(FinanceEvent.LoadCategories(true))
            }

    fun processEvent(event: FinanceEvent) {
        when (event) {
            is FinanceEvent.SaveTransaction -> saveTransaction(event.transaction)
            is FinanceEvent.LoadCategories -> loadCategories(event)
            is FinanceEvent.UpdateCategories -> updateCategories(event)
        }
    }

    private fun saveTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.saveTransaction(transaction)
            mutableSharedFlow.emit(FinanceEffect.ShowSuccessBanner(UiText.SimpleString("Transaction saved")))
        }
    }

    private fun loadCategories(event: FinanceEvent.LoadCategories) {
        viewModelScope.launch {
            val categories = useCase(event.isIncome)
            mutableUiState.value = FinanceUiState.CategoriesLoaded(categories)
        }
    }

    private fun updateCategories(event: FinanceEvent.UpdateCategories) {
        viewModelScope.launch {
            val categories = useCase(event.isIncome)
            mutableSharedFlow.emit(FinanceEffect.CategoriesUpdated(categories))
        }
    }
}