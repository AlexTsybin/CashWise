package com.alextsy.main.data.di

import com.alextsy.main.data.repository.UserPreferenceRepoImpl
import com.alextsy.main.domain.UserPreferenceRepository
import com.alextsy.storage.datastore.di.preferenceModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mainDataModule = module {

    includes(preferenceModule)

    singleOf(::UserPreferenceRepoImpl) bind UserPreferenceRepository::class
}