package com.alextsy.main.data.repository

import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.OnboardingConfig
import com.alextsy.common.model.ThemeConfig
import com.alextsy.main.domain.UserPref
import com.alextsy.main.domain.UserPreferenceRepository
import com.alextsy.storage.datastore.data.AuthStatus
import com.alextsy.storage.datastore.data.OnboardingStatus
import com.alextsy.storage.datastore.data.Theme
import com.alextsy.storage.datastore.user.UserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserPreferenceRepoImpl(
    private val source: UserDataSource
) : UserPreferenceRepository {

    override fun getUserPreference(): Flow<UserPref> = flow {
        emit(
            source.userData().first().let {
                UserPref(
                    onboardingStatus = when (it.onboardingStatus) {
                        OnboardingStatus.NOT_STARTED -> OnboardingConfig.NOT_STARTED
                        OnboardingStatus.IN_PROGRESS -> OnboardingConfig.IN_PROGRESS
                        OnboardingStatus.COMPLETED -> OnboardingConfig.COMPLETED
                        OnboardingStatus.UNRECOGNIZED, null -> OnboardingConfig.NOT_STARTED
                    },
                    authType = when (it.authStatus) {
                        AuthStatus.NOT_AUTHENTICATED -> AuthConfig.NOT_AUTHENTICATED
                        AuthStatus.AUTHENTICATED -> AuthConfig.AUTHENTICATED
                        AuthStatus.GUEST -> AuthConfig.GUEST
                        AuthStatus.UNRECOGNIZED, null -> AuthConfig.NOT_AUTHENTICATED
                    },
                    theme = when (it.theme) {
                        Theme.FOLLOW_SYSTEM -> ThemeConfig.SYSTEM
                        Theme.LIGHT -> ThemeConfig.LIGHT
                        Theme.DARK -> ThemeConfig.DARK
                        else -> ThemeConfig.SYSTEM
                    },
                    isNotificationEnabled = it.isNotificationEnabled,
                    defaultCurrency = it.defaultCurrency
                )
            }
        )
    }
}