package com.alextsy.designsystem.component.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.theme.graphExpense
import com.alextsy.designsystem.theme.graphIncome

@Composable
fun AmountTextField(
    defaultCurrency: String,
    value: String,
    isIncome: Boolean = true,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        value = value,
        onValueChange = { newValue ->
            if (newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        textStyle = TextStyle(
            color = if (isIncome) graphIncome else graphExpense,
            fontSize = 48.sp,
            textAlign = TextAlign.End,
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd,
            ) {
                if (value.isEmpty()) {
                    CwText(
                        text = "$defaultCurrency 0",
                        textType = if (isIncome) {
                            TextType.DISPLAY_MEDIUM_INCOME
                        } else {
                            TextType.DISPLAY_MEDIUM_EXPENSE
                        },
                    )
                } else {
                    Text(
                        text = "$defaultCurrency$value",
                        style = TextStyle(
                            color = if (isIncome) graphIncome else graphExpense,
                            fontSize = 48.sp,
                        ),
                    )
                }
                innerTextField()
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}

@CwPreview
@Composable
fun AmountTextFieldPreview() {
    PreviewSurface {
        AmountTextField(
            value = "1000",
            onValueChange = {},
            defaultCurrency = "$",
            isIncome = true,
        )
    }
}