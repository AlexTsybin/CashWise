package com.alextsy.main.domain

import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {

    fun getUserPreference(): Flow<UserPref>
}