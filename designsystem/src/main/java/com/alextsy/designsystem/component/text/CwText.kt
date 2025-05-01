package com.alextsy.designsystem.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface

@Composable
fun CwText(
    text: String,
    textType: TextType,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(
        text = text,
        textAlign = textAlign,
        style = textType.getStyle(),
        color = textType.getColor()
    )
}

@Composable
fun CwText(
    text: AnnotatedString,
    textType: TextType,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = textType.getStyle()
    )
}

@CwPreview
@Composable
fun AllTextPreview() {
    val text = LoremIpsum(10).values.first()
    PreviewSurface {
        CwText(text, TextType.BUTTON_LABEL)
        CwText(text, TextType.LABEL_SMALL)
        CwText(text, TextType.TITLE)
        CwText(text, TextType.LABEL_LARGE)
        CwText(text, TextType.HEADLINE_SMALL)
        CwText(text, TextType.DISPLAY_SMALL_GRADIENT)
        CwText(
            text,
            TextType.BUTTON_LABEL,
            textAlign = TextAlign.Center
        )
    }
}