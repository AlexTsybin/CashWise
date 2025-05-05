package com.alextsy.onboarding.presentation.mvi

sealed interface Event {
    data object OnboardingInProgress : Event
    data object OnboardingCompleted : Event
}