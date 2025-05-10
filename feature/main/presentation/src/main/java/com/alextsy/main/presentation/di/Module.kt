package com.alextsy.main.presentation.di

import com.alextsy.main.presentation.mvi.MainViewModel
import com.alextsy.onboarding.presentation.di.onboardingModule
import com.alextsy.records.presentation.addrecords.di.recordsPresentationModule
import com.alextsy.records.presentation.transactionCategory.di.transactionCategoryPresentationModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    includes(onboardingModule)
    includes(recordsPresentationModule)
    includes(transactionCategoryPresentationModule)
    viewModel {
        MainViewModel(get())
    }
}