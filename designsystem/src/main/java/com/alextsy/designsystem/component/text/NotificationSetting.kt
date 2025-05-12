package com.alextsy.designsystem.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alextsy.designsystem.theme.LocalDimensions

@Composable
fun NotificationSetting(title: String) {
    var isChecked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = LocalDimensions.current.dimensions12),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CwText(
            text = title,
            textType = TextType.LABEL_LARGE,
        )
        Switch(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
            },
            colors = SwitchDefaults.colors(checkedThumbColor = Color.White),
        )
    }
}