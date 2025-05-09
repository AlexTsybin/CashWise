package com.alextsy.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.alextsy.common.model.records.Category
import com.alextsy.designsystem.R
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
private fun SubCategoryChip(
    text: String,
    icon: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.surfaceContainerHigh
    } else {
        MaterialTheme.colorScheme.surface
    }

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(LocalDimensions.current.dimensions64))
            .border(
                width = LocalDimensions.current.dimensions2,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(LocalDimensions.current.dimensions64),
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(LocalDimensions.current.dimensions64),
            )
            .padding(
                horizontal = LocalDimensions.current.dimensions12,
                vertical = LocalDimensions.current.dimensions8,
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = icon,
            color = Color.White,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions4))
        CwText(
            text = text,
            textType = TextType.LABEL_SMALL,
        )
    }
}

@Composable
fun Subcategories(
    subcategories: List<Category>,
    onSelect: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    val selectedItemIndex = remember { mutableIntStateOf(-1) }

    Column {
        CwText(
            text = stringResource(id = R.string.txt_sub_category),
            textType = TextType.LABEL_SMALL,
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8),
        ) {
            subcategories.forEachIndexed { index, tabText ->
                SubCategoryChip(
                    isSelected = selectedItemIndex.intValue == index,
                    text = tabText.name,
                    icon = tabText.icon,
                ) {
                    selectedItemIndex.intValue = index
                    onSelect(tabText.id)
                }
            }
        }
    }
}

@CwPreview
@Composable
fun SubcategoriesPreviews() {
    val sc = listOf(
        Category(name = "Food", icon = "🍔", id = 0),
        Category(name = "Transport", icon = "🚙", id = 1),
        Category(name = "Shopping", icon = "🛍", id = 2),
        Category(name = "Electricity", icon = "💡", id = 3),
        Category(name = "Salary", icon = "💰", id = 4),
    )
    PreviewSurface {
        Subcategories(
            sc,
            onSelect = {},
        )
    }
}