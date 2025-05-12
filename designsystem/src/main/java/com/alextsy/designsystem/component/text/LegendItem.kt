package com.alextsy.designsystem.component.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun LegendItem(
    label: String,
    color: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(LocalDimensions.current.dimensions12)
                .background(
                    color = color,
                    shape = RoundedCornerShape(6.dp)
                )
        )
        Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions4))
        CwText(
            text = label,
            textType = TextType.LABEL_LARGE
        )
    }
}