package com.alextsy.cashwise.di

import com.alextsy.main.data.di.mainDataModule
import com.alextsy.main.presentation.di.presentationModule
import com.alextsy.onboarding.data.di.onboardingDataModule
import com.alextsy.records.data.di.recordsDataModule
import org.koin.dsl.module

val appModule = module {
    includes(mainDataModule)
    includes(onboardingDataModule)
    includes(recordsDataModule)
    includes(presentationModule)
}