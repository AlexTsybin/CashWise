package com.alextsy.onboarding.data.di

import com.alextsy.onboarding.data.repository.UserPreferenceRepositoryImpl
import com.alextsy.onboarding.domain.UserPreferenceRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val onboardingDataModule = module {
    singleOf(::UserPreferenceRepositoryImpl) bind UserPreferenceRepository::class
}