package com.alextsy.network.firebase_auth.di

import com.alextsy.network.firebase_auth.AuthenticationHelper
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    single { AuthenticationHelper(get()) }
}