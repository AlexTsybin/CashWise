package com.alextsy.common.model

sealed interface SignInError {
    data object InvalidCredentials : SignInError
    data object InvalidUser : SignInError
    data object UnknownError : SignInError
}