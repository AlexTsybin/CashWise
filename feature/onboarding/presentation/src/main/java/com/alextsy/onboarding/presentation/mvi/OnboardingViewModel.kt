package com.alextsy.onboarding.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alextsy.common.model.OnboardingConfig
import com.alextsy.onboarding.domain.UserPreferenceRepository
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val preferenceRepository: UserPreferenceRepository
) : ViewModel() {

    fun event(event: Event) {
        when (event) {
            Event.OnboardingInProgress -> viewModelScope.launch {
                preferenceRepository.saveUserOnboardingStatus(OnboardingConfig.COMPLETED)
            }
            Event.OnboardingCompleted -> viewModelScope.launch {
                preferenceRepository.saveUserOnboardingStatus(OnboardingConfig.IN_PROGRESS)
            }
        }
    }
}