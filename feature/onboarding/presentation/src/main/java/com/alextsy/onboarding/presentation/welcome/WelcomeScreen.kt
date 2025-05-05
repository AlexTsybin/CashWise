package com.alextsy.onboarding.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.alextsy.designsystem.component.button.CwButton
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.onboarding.presentation.R
import com.alextsy.onboarding.presentation.mvi.Event
import com.alextsy.onboarding.presentation.mvi.OnboardingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    onNextScreen: () -> Unit,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    Welcome(onNextScreen, viewModel::event)
}

@Composable
private fun Welcome(
    onNextScreen: () -> Unit,
    dispatch: (Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = LocalDimensions.current.dimensions16,
                top = LocalDimensions.current.dimensions16,
                end = LocalDimensions.current.dimensions16,
                bottom = LocalDimensions.current.dimensions32
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = LocalDimensions.current.dimensions48),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = com.alextsy.designsystem.R.drawable.welcome_logo),
                modifier = Modifier
                    .size(LocalDimensions.current.dimensions128)
                    .padding(LocalDimensions.current.dimensions4)
                    .clip(CircleShape),
                contentDescription = "condition"
            )
            Column(
                modifier = Modifier
                    .padding(start = LocalDimensions.current.dimensions2)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8),
                horizontalAlignment = Alignment.End
            ) {
                CwText(
                    text = stringResource(R.string.welcome_text),
                    textType = TextType.TITLE
                )
                CwText(
                    text = stringResource(R.string.welcome_app_name),
                    textType = TextType.DISPLAY_SMALL_GRADIENT
                )
                CwText(
                    text = stringResource(R.string.welcome_description),
                    textType = TextType.LABEL_SMALL
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        CwButton(
            text = stringResource(R.string.welcome_get_started),
            isFullWidth = false,
            onClick = {
                dispatch(Event.OnboardingInProgress)
                onNextScreen()
            }
        )
    }
}

@CwPreview
@Composable
private fun WelcomeScreenPreview() {
    PreviewSurface {
        WelcomeScreen(
            onNextScreen = {}
        )
    }
}