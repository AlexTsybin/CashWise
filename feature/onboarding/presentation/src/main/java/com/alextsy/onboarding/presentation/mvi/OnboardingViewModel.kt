package com.alextsy.onboarding.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextsy.common.model.OnboardingConfig
import com.alextsy.common.model.SignInError
import com.alextsy.designsystem.utility.UiText
import com.alextsy.onboarding.domain.AuthRepository
import com.alextsy.onboarding.domain.UserPreferenceRepository
import com.alextsy.onboarding.presentation.R
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val preferenceRepository: UserPreferenceRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val mutableSharedFlow: MutableSharedFlow<Effects> = MutableSharedFlow()
    val effect: SharedFlow<Effects>
        get() = mutableSharedFlow.asSharedFlow()

    fun event(event: Event) {
        when (event) {
            Event.OnboardingInProgress -> viewModelScope.launch {
                preferenceRepository.saveUserOnboardingStatus(OnboardingConfig.COMPLETED)
            }
            Event.OnboardingCompleted -> viewModelScope.launch {
                preferenceRepository.saveUserOnboardingStatus(OnboardingConfig.IN_PROGRESS)
            }
            Event.SignInAnonymous -> viewModelScope.launch {
                authRepository.signInAnonymous().fold(
                    { signInError ->
                        mutableSharedFlow.emit(Effects.ShowError(getErrorMessage(signInError)))
                    },
                    { authConfig ->
                        preferenceRepository.saveUserAuth(authConfig)
                        mutableSharedFlow.emit(Effects.NavigateToHome)
                    },
                )
            }
            is Event.SignInWithGoogle -> viewModelScope.launch {
                if (event.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(event.credential.data)
                    authRepository.signIn(googleIdTokenCredential.idToken)
                        .fold(
                            { signInError ->
                                mutableSharedFlow.emit(Effects.ShowError(getErrorMessage(signInError)))
                            },
                            { authConfig ->
                                preferenceRepository.saveUserAuth(authConfig)
                                mutableSharedFlow.emit(Effects.NavigateToHome)
                            },
                        )
                }
            }
        }
    }

    private fun getErrorMessage(signInError: SignInError) =
        when (signInError) {
            SignInError.InvalidCredentials -> UiText.StringResource(R.string.error_invalid_credentials)
            SignInError.InvalidUser -> UiText.StringResource(R.string.error_invalid_user)
            SignInError.UnknownError -> UiText.StringResource(R.string.error_unknown)
        }
}