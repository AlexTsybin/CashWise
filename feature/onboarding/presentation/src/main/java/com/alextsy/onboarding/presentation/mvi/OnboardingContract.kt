package com.alextsy.onboarding.presentation.mvi

import androidx.credentials.Credential
import com.alextsy.designsystem.utility.UiText

sealed interface Event {
    data object OnboardingInProgress : Event
    data object OnboardingCompleted : Event
    data object SignInAnonymous : Event
    data class SignInWithGoogle(val credential: Credential) : Event
}

sealed interface Effects {
    data object NavigateToHome : Effects
    data class ShowError(val message: UiText) : Effects
}