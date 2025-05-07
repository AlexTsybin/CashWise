package com.alextsy.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alextsy.main.presentation.mvi.MainViewModel
import com.alextsy.main.presentation.mvi.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: UiState by mutableStateOf(UiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .collect { uiState = it }
//                    .onEach { uiState = it }
//                    .collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                UiState.Loading -> true
                is UiState.Success -> false
            }
        }

        enableEdgeToEdge()
        setContent {
            if (uiState is UiState.Success) {
                CwApp(uiState as UiState.Success)
            }
        }
    }
}