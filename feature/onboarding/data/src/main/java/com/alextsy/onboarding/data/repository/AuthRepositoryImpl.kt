package com.alextsy.onboarding.data.repository

import arrow.core.Either
import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.SignInError
import com.alextsy.common.model.User
import com.alextsy.network.firebase_auth.AuthenticationHelper
import com.alextsy.onboarding.domain.AuthRepository

class AuthRepositoryImpl(
    private val helper: AuthenticationHelper,
) : AuthRepository {

    override val currentUser: User?
        get() = helper.getCurrentUser()

    override suspend fun signIn(token: String): Either<SignInError, AuthConfig> = helper.signInWithGoogle(token)

    override suspend fun signInAnonymous(): Either<SignInError, AuthConfig> = helper.signInAnonymous()
}