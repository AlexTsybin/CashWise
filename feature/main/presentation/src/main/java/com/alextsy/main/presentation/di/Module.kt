package com.alextsy.main.presentation.di

import com.alextsy.main.presentation.mvi.MainViewModel
import com.alextsy.onboarding.presentation.di.onboardingModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    includes(onboardingModule)

    viewModel {
        MainViewModel(get())
    }
}