package com.alextsy.main.domain

import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.OnboardingConfig
import com.alextsy.common.model.ThemeConfig

data class UserPref(
    val onboardingStatus: OnboardingConfig,
    val authType: AuthConfig,
    val theme: ThemeConfig,
    val defaultCurrency: String,
    val isNotificationEnabled: Boolean
)
