package com.alextsy.onboarding.data.repository

import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.OnboardingConfig
import com.alextsy.onboarding.domain.UserPreferenceRepository
import com.alextsy.storage.datastore.user.UserDataSource

class UserPreferenceRepositoryImpl(
    private val source: UserDataSource,
) : UserPreferenceRepository {

    override suspend fun saveUserAuth(authType: AuthConfig) = source.setAuthType(authType)

    override suspend fun saveUserOnboardingStatus(onboardingStatus: OnboardingConfig) = source.setOnboardingStatus(onboardingStatus)
}