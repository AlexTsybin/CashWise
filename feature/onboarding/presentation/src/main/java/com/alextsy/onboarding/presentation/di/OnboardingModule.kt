package com.alextsy.onboarding.presentation.di

import com.alextsy.onboarding.presentation.mvi.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val onboardingModule = module {
    viewModel {
        OnboardingViewModel(get(), get())
    }
}