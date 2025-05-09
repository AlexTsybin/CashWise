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
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alextsy.designsystem.R
import com.alextsy.designsystem.component.preview.CwPreview
import com.alextsy.designsystem.component.preview.PreviewSurface
import com.alextsy.designsystem.theme.LocalDimensions
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTextField(
    label: String,
    onDateSelected: (Long) -> Unit,
) {
    var selectedDateMillis by rememberSaveable { mutableLongStateOf(LocalDate.now().toEpochDay() * 24 * 60 * 60 * 1000) }
    var showDatePicker by remember { mutableStateOf(false) }
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val selectedDate = remember(selectedDateMillis) {
        LocalDate.ofEpochDay(selectedDateMillis / (24 * 60 * 60 * 1000))
    }
    val selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDateMillis,
        yearRange = IntRange(1900, LocalDate.now().year),
        selectableDates = selectableDates,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDatePicker = true },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            CwText(
                text = label,
                textType = TextType.LABEL_SMALL,
            )
            Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
            CwText(
                text = selectedDate.format(formatter),
                textType = TextType.LABEL_LARGE,
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
            imageVector = Icons.Default.CalendarToday,
            contentDescription = stringResource(R.string.txt_select_date),
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                        datePickerState.selectedDateMillis?.let {
                            selectedDateMillis = it
                            onDateSelected(it)
                        }
                    },
                ) {
                    CwText(
                        text = stringResource(R.string.txt_ok),
                        textType = TextType.LABEL_LARGE,
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    },
                ) {
                    CwText(
                        text = stringResource(R.string.txt_cancel),
                        textType = TextType.LABEL_LARGE,
                    )
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@CwPreview
@Composable
fun CalendarTextFieldPreview() {
    PreviewSurface {
        CalendarTextField(
            label = "Select Date",
            onDateSelected = {},
        )
    }
}