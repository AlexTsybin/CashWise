package com.alextsy.onboarding.presentation.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import com.alextsy.designsystem.component.animation.Lottie
import com.alextsy.designsystem.component.button.ButtonType
import com.alextsy.designsystem.component.button.CwButton
import com.alextsy.designsystem.component.pager.PagerWithIndicator
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.SpanType
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.component.text.createAnnotatedString
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.onboarding.presentation.R
import com.alextsy.onboarding.presentation.mvi.Event
import com.alextsy.onboarding.presentation.mvi.OnboardingViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun IntroductionScreen(
    onNextScreen: () -> Unit,
    viewModel: OnboardingViewModel = koinViewModel(),
) {
    Onboarding(onNextScreen, viewModel::event)
}

@Composable
private fun Onboarding(
    onNextScreen: () -> Unit,
    dispatch: (Event) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.dimensions16),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val coroutineScope = rememberCoroutineScope()
        val pageCount = stringArrayResource(R.array.onboarding_title).size
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = { pageCount },
        )
        val buttonText = if (pagerState.currentPage == pageCount - 1) {
            stringResource(R.string.welcome_get_started)
        } else {
            stringResource(R.string.next)
        }

        PagerWithIndicator(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = LocalDimensions.current.dimensions24),
            pageCount = pageCount,
            pagerState = pagerState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = LocalDimensions.current.dimensions16),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Lottie(
                    lottieUrl = stringArrayResource(R.array.onboarding_animation)[it],
                    modifier = Modifier.size(LocalDimensions.current.dimensions300),
                )
                Spacer(modifier = Modifier.weight(1f))
                CwText(
                    text = createAnnotatedString(
                        fullText = stringArrayResource(R.array.onboarding_title)[it],
                        spans = listOf(
                            SpanType.HeadingSpan(
                                text = stringArrayResource(R.array.onboarding_title)[it].split(" ")[0],
                                styles = SpanStyle(color = MaterialTheme.colorScheme.primary),
                            ),
                        ),
                    ),
                    textType = TextType.HEADLINE_SMALL,
                )
                Spacer(modifier = Modifier.padding(LocalDimensions.current.dimensions8))
                CwText(
                    text = stringArrayResource(R.array.onboarding_description)[it],
                    textType = TextType.LABEL_SMALL,
                )
                Spacer(modifier = Modifier.padding(vertical = LocalDimensions.current.dimensions24))
            }
        }
        CwButton(buttonText) {
            coroutineScope.launch {
                if (pagerState.currentPage < pageCount - 1) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                } else {
                    dispatch(Event.OnboardingCompleted)
                    onNextScreen.invoke()
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = LocalDimensions.current.dimensions4))
        CwButton(
            text = stringResource(R.string.skip),
            isFullWidth = true,
            buttonType = ButtonType.TEXT,
        ) {
            dispatch(Event.OnboardingCompleted)
            onNextScreen.invoke()
        }
    }
}

@CwPreview
@Composable
private fun IntroductionScreenPreview() {
    PreviewSurface {
        IntroductionScreen(
            onNextScreen = {},
        )
    }
}