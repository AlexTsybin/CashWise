package com.alextsy.onboarding.domain

import arrow.core.Either
import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.SignInError
import com.alextsy.common.model.User

interface AuthRepository {

    val currentUser: User?

    suspend fun signIn(token: String): Either<SignInError, AuthConfig>

    suspend fun signInAnonymous(): Either<SignInError, AuthConfig>
}