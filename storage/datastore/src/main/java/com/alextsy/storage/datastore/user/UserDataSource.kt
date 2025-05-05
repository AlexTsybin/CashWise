package com.alextsy.storage.datastore.user

import androidx.datastore.core.DataStore
import com.alextsy.common.model.AuthConfig
import com.alextsy.common.model.OnboardingConfig
import com.alextsy.common.model.ThemeConfig
import com.alextsy.storage.datastore.data.AuthStatus
import com.alextsy.storage.datastore.data.OnboardingStatus
import com.alextsy.storage.datastore.data.Theme
import com.alextsy.storage.datastore.data.UserPreferences
import com.alextsy.storage.datastore.data.copy
import kotlinx.coroutines.flow.Flow

class UserDataSource(
    private val datastore: DataStore<UserPreferences>
) {

    fun userData(): Flow<UserPreferences> {
        return datastore.data
    }

    suspend fun setTheme(config: ThemeConfig) {
        datastore.updateData {
            it.copy {
                theme = when (config) {
                    ThemeConfig.SYSTEM -> Theme.FOLLOW_SYSTEM
                    ThemeConfig.LIGHT -> Theme.LIGHT
                    ThemeConfig.DARK -> Theme.DARK
                }
            }
        }
    }

    suspend fun setAuthType(type: AuthConfig) {
        datastore.updateData {
            it.copy {
                authStatus = when (type) {
                    AuthConfig.NOT_AUTHENTICATED -> AuthStatus.NOT_AUTHENTICATED
                    AuthConfig.AUTHENTICATED -> AuthStatus.AUTHENTICATED
                    AuthConfig.GUEST -> AuthStatus.GUEST
                }
            }
        }
    }

    suspend fun setOnboardingStatus(status: OnboardingConfig) {
        datastore.updateData {
            it.copy {
                onboardingStatus = when (status) {
                    OnboardingConfig.NOT_STARTED -> OnboardingStatus.NOT_STARTED
                    OnboardingConfig.IN_PROGRESS -> OnboardingStatus.IN_PROGRESS
                    OnboardingConfig.COMPLETED -> OnboardingStatus.COMPLETED
                }
            }
        }
    }

    suspend fun setDefaultCurrency(currency : String) {
        datastore.updateData {
            it.copy {
                defaultCurrency = currency
            }
        }
    }

    suspend fun setIsNotificationEnabled(enabled: Boolean) {
        datastore.updateData {
            it.copy {
                isNotificationEnabled = enabled
            }
        }
    }
}