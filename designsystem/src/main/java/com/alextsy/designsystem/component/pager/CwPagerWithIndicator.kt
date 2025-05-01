package com.alextsy.designsystem.component.pager

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.LocalShapes

@Composable
fun PagerWithIndicator(
    pageCount: Int,
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    content: @Composable (pageIndex: Int) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState
        ) { page ->
            content(page)
        }

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        PagerIndicator(
            currentPage = pagerState.currentPage,
            pageCount = pageCount
        )
    }
}

@Composable
fun PagerIndicator(
    currentPage: Int,
    pageCount: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            val shape = if (isSelected) {
                LocalShapes.current.pagerIndicator
            } else {
                LocalShapes.current.dots
            }
            val width = animateDpAsState(
                targetValue = if (isSelected) {
                    LocalDimensions.current.dimensions16
                } else {
                    LocalDimensions.current.dimensions8
                },
                label = "widthAnimation"
            )
            val color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            }

            Box(
                modifier = Modifier
                    .width(width.value)
                    .size(LocalDimensions.current.dimensions8)
                    .clip(shape)
                    .background(color)
            )
        }
    }
}

@CwPreview
@Composable
fun PagerIndicatorWithPreview() {
    val pagerState = rememberPagerState(
        pageCount = { 5 }
    )
    PreviewSurface {
        PagerWithIndicator(
            pageCount = 5,
            pagerState = pagerState
        ) { pageIndex ->
            CwText(
                text = "Page $pageIndex",
                textType = TextType.LABEL_LARGE
            )
        }
    }
}