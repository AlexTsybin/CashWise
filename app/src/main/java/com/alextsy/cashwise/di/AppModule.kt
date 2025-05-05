package com.alextsy.cashwise.di

import com.alextsy.main.data.di.mainDataModule
import com.alextsy.main.presentation.di.presentationModule
import com.alextsy.onboarding.data.di.onboardingDataModule
import org.koin.dsl.module

val appModule = module {
    includes(mainDataModule)
    includes(onboardingDataModule)
    includes(presentationModule)
}