package com.alextsy.onboarding.domain

import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.OnboardingConfig

interface UserPreferenceRepository {

    suspend fun saveUserAuth(authType: AuthConfig)

    suspend fun saveUserOnboardingStatus(onboardingStatus: OnboardingConfig)
}