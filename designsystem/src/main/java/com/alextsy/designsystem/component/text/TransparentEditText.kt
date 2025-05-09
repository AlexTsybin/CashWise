package com.alextsy.designsystem.component.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun TransparentEditText(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    hint: String,
) {
    Column {
        CwText(
            text = label,
            textType = TextType.LABEL_SMALL,
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.onSurface),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    if (value.isEmpty()) {
                        CwText(
                            text = hint,
                            textType = TextType.LABEL_LARGE,
                        )
                    }
                    innerTextField()
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@CwPreview
@Composable
fun TransparentEditTextPreview() {
    PreviewSurface {
        TransparentEditText(
            value = "1000",
            onValueChange = {},
            label = "Label",
            hint = "Hint",
        )
    }
}