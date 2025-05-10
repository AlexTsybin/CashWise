package com.alextsy.records.presentation.transactionCategory.di

import com.alextsy.records.domain.usecase.GetCategoryUseCase
import com.alextsy.records.presentation.transactionCategory.mvi.TransactionCategoryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val transactionCategoryPresentationModule = module {
    single {
        GetCategoryUseCase(get())
    }
    viewModel {
        TransactionCategoryViewModel(get())
    }
}