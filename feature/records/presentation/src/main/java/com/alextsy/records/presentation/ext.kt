package com.alextsy.records.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.stateInWhileActive(
    scope: CoroutineScope,
    initial: T,
    prefetch: () -> Unit,
): StateFlow<T> {
    return onStart {
        prefetch()
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = initial
    )
}