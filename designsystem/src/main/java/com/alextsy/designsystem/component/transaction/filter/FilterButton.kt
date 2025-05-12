package com.alextsy.designsystem.component.transaction.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun FilterButton(
    text: String,
    isSelected: Boolean = false,
    onFilterSelected: () -> Unit,
) {
    Row(
        modifier = Modifier
            .defaultMinSize(
                minWidth = 50.dp,
                minHeight = 32.dp,
            )
            .clip(RoundedCornerShape(LocalDimensions.current.dimensions64))
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(LocalDimensions.current.dimensions64),
            )
            .background(
                color = if (isSelected) {
                    MaterialTheme.colorScheme.surfaceContainerHigh
                } else {
                    MaterialTheme.colorScheme.surface
                },
                shape = RoundedCornerShape(LocalDimensions.current.dimensions64),
            )
            .padding(
                horizontal = LocalDimensions.current.dimensions12,
                vertical = LocalDimensions.current.dimensions8,
            )
            .clickable {
                onFilterSelected()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        CwText(
            text = text,
            textType = TextType.LABEL_SMALL,
            textAlign = TextAlign.Center,
        )
    }
}