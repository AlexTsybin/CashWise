package com.alextsy.designsystem.component.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alextsy.designsystem.R
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun SelectCategory(
    categoryType: String,
    onSelectCategory: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectCategory() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            CwText(
                text = stringResource(id = R.string.txt_category),
                textType = TextType.LABEL_SMALL,
            )
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
            CwText(
                text = categoryType,
                textType = TextType.LABEL_LARGE_PRIMARY,
            )
        }
        Icon(
            modifier = Modifier
                .size(LocalDimensions.current.dimensions32)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = CircleShape,
                )
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = CircleShape,
                )
                .padding(LocalDimensions.current.dimensions8),
            imageVector = Icons.Default.AddCard,
            contentDescription = categoryType,
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@CwPreview
@Composable
fun SelectCategoryPreview() {
    PreviewSurface {
        SelectCategory(
            "Food",
            onSelectCategory = {},
        )
    }
}