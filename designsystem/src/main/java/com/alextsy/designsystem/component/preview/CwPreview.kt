package com.alextsy.designsystem.component.preview

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION,
)
@Preview(
    name = "0.85",
    device = Devices.PIXEL_7,
    showBackground = true,
    showSystemUi = true,
    fontScale = 0.85f,
    uiMode = UI_MODE_NIGHT_NO,
)
@Preview(
    name = "1.0",
    device = Devices.PIXEL_7,
    showBackground = true,
    showSystemUi = true,
    fontScale = 1.0f,
    uiMode = UI_MODE_NIGHT_YES,
)
@Preview(
    name = "1.5",
    device = Devices.PIXEL_7,
    showSystemUi = true,
    fontScale = 1.5f,
    uiMode = UI_MODE_NIGHT_YES,
)
@Preview(
    name = "2.0",
    device = Devices.PIXEL_7,
    showSystemUi = true,
    fontScale = 2f,
    uiMode = UI_MODE_NIGHT_NO,
)
annotation class CwPreview