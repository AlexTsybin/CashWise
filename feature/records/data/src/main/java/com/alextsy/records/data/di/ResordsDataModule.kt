package com.alextsy.records.data.di

import com.alextsy.records.data.repository.UpdateFinanceRepositoryImpl
import com.alextsy.records.domain.repository.UpdateFinanceRepository
import com.alextsy.storage.database.di.roomModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val recordsDataModule = module {
    includes(roomModule)
    singleOf(::UpdateFinanceRepositoryImpl) bind UpdateFinanceRepository::class
}