package com.alextsy.onboarding.presentation.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.alextsy.designsystem.component.button.ButtonType
import com.alextsy.designsystem.component.button.CwButton
import com.alextsy.designsystem.component.button.GoogleButton
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.SpanType
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.component.text.createAnnotatedString
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.utility.UiText
import com.alextsy.onboarding.presentation.R
import com.alextsy.onboarding.presentation.mvi.Effects
import com.alextsy.onboarding.presentation.mvi.Event
import com.alextsy.onboarding.presentation.mvi.OnboardingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    viewModel: OnboardingViewModel = koinViewModel<OnboardingViewModel>(),
    onNextScreen: () -> Unit,
    onError: (Boolean, UiText) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect {
            when (it) {
                Effects.NavigateToHome -> onNextScreen()
                is Effects.ShowError -> onError(true, it.message)
            }
        }
    }
    SignIn(onNextScreen, onError, viewModel::event)
}

@Composable
fun SignIn(
    onNextScreen: () -> Unit,
    onError: (Boolean, UiText) -> Unit,
    dispatcher: (Event) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.dimensions16),
    ) {
        CwText(
            text = createAnnotatedString(
                fullText = stringResource(R.string.sign_in_title_text),
                spans = listOf(
                    SpanType.HeadingSpan(
                        text = stringResource(R.string.sign_in_title_span),
                        styles = SpanStyle(color = MaterialTheme.colorScheme.primary),
                    ),
                ),
            ),
            textType = TextType.DISPLAY_MEDIUM,
            modifier = Modifier.padding(
                top = LocalDimensions.current.dimensions48,
                start = LocalDimensions.current.dimensions16,
            ),
        )
        Spacer(modifier = Modifier.weight(1f))
        GoogleButton(
            text = stringResource(R.string.sign_in_google_button),
            onSuccess = { dispatcher(Event.SignInWithGoogle(it)) },
            onFailure = {
                onError(true, UiText.SimpleString(it))
            },
        )
        CwButton(
            text = stringResource(R.string.sign_in_guest_button),
            isFullWidth = true,
            buttonType = ButtonType.TEXT,
        ) {
            dispatcher(Event.SignInAnonymous)
        }
        CwText(
            text = createAnnotatedString(
                fullText = stringResource(R.string.sign_in_tnc_text),
                spans = listOf(
                    SpanType.UrlSpan(
                        text = stringResource(R.string.sign_in_tnc_span_1),
                        url = "https://www.google.com",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold,
                            ),
                        ),
                    ),
                    SpanType.UrlSpan(
                        text = stringResource(R.string.sign_in_tnc_span_2),
                        url = "https://www.google.com",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold,
                            ),
                        ),
                    ),
                ),
            ),
            textType = TextType.LABEL_SMALL,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(LocalDimensions.current.dimensions16),
        )
    }
}

@CwPreview
@Composable
private fun SignInScreenPreview() {
    PreviewSurface {
        SignInScreen(
            onNextScreen = {},
            onError = { _, _ -> },
        )
    }
}