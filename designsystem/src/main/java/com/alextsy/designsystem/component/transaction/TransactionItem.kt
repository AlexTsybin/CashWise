package com.alextsy.designsystem.component.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alextsy.common.model.records.RecordTransactionType
import com.alextsy.designsystem.component.ext.getTextColor
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun TransactionItem(
    icon: String,
    category: String,
    subcategory: String,
    amount: String,
    recordTransactionType: RecordTransactionType,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = LocalDimensions.current.dimensions8),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TransactionCategoryItem(icon = icon)
        Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions16))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            CwText(
                text = category,
                textType = TextType.LABEL_LARGE,
            )
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions4))
            CwText(
                text = subcategory,
                textType = TextType.LABEL_SMALL,
            )
        }
        Text(
            text = amount,
            color = recordTransactionType.getTextColor(),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}