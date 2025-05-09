package com.alextsy.storage.database.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alextsy.storage.database.FinanceDatabase
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            get(),
            FinanceDatabase::class.java,
            "finance_db",
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                FinanceDatabase.prepopulateDatabase(get())
            }
        })
            .build()
    }

    single { get<FinanceDatabase>().financeDao() }
}