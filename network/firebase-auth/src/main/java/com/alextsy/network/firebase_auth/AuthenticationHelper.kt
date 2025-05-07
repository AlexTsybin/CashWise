package com.alextsy.network.firebase_auth

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.SignInError
import com.alextsy.common.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class AuthenticationHelper(
    private val auth: FirebaseAuth,
) {

    fun getCurrentUser(): User? =
        auth.currentUser?.let {
            User(
                displayName = it.displayName.orEmpty(),
                displayProfileUrl = it.photoUrl.toString(),
            )
        }

    suspend fun signInAnonymous(): Either<SignInError, AuthConfig> {
        return runCatching {
            auth.signInAnonymously().await()
            AuthConfig.AUTHENTICATED.right()
        }.getOrElse { e ->
            SignInError.UnknownError.left()
        }
    }

    suspend fun signInWithGoogle(token: String): Either<SignInError, AuthConfig> {
        val authCredential = GoogleAuthProvider.getCredential(token, null)
        return runCatching {
            auth.signInWithCredential(authCredential).await()
            AuthConfig.AUTHENTICATED.right()
        }.getOrElse { e ->
            when (e) {
                is FirebaseAuthInvalidCredentialsException -> SignInError.InvalidCredentials.left()
                is FirebaseAuthInvalidUserException -> SignInError.InvalidUser.left()
                else -> SignInError.UnknownError.left()
            }
        }
    }
}