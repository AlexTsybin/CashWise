package com.alextsy.records.presentation.addrecords.di

import com.alextsy.records.domain.usecase.GetDefaultCategoryUseCase
import com.alextsy.records.presentation.addrecords.mvi.RecordsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val recordsPresentationModule = module {
    single {
        GetDefaultCategoryUseCase(get())
    }
    viewModel {
        RecordsViewModel(get(), get())
    }
}