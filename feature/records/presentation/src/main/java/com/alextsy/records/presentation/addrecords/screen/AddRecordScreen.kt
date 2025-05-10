package com.alextsy.records.presentation.addrecords.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alextsy.common.model.records.Categorization
import com.alextsy.common.model.records.RecordTransactionType
import com.alextsy.common.model.records.Transaction
import com.alextsy.designsystem.component.button.CwButton
import com.alextsy.designsystem.component.chip.Subcategories
import com.alextsy.designsystem.component.tab.AnimatedTab
import com.alextsy.designsystem.component.text.AmountEditText
import com.alextsy.designsystem.component.text.CalendarTextField
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.SelectCategory
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.component.text.TransparentEditText
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.utility.UiText
import com.alextsy.records.presentation.R
import com.alextsy.records.presentation.addrecords.mvi.FinanceEffect
import com.alextsy.records.presentation.addrecords.mvi.FinanceEvent
import com.alextsy.records.presentation.addrecords.mvi.FinanceUiState
import com.alextsy.records.presentation.addrecords.mvi.RecordsViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun AddRecordScreen(
    viewModel: RecordsViewModel = koinViewModel<RecordsViewModel>(),
    defaultCurrency: String = stringResource(R.string.default_currency),
    categorization: Categorization?,
    onSelectCategory: (Boolean) -> Unit,
    onCategoryChange: (Categorization) -> Unit,
    showBanner: (Boolean, UiText, Boolean) -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is FinanceEffect.CategoriesUpdated -> onCategoryChange(it.categories)
                is FinanceEffect.ShowSuccessBanner -> showBanner(true, it.text, false)
            }
        }
    }

    when (val state = state.value) {
        is FinanceUiState.CategoriesLoaded -> AddRecords(
            defaultCurrency,
            categorization ?: state.categories,
            onSelectCategory,
            viewModel::processEvent,
        )
        FinanceUiState.Initial -> LinearProgressIndicator()
    }
}

@Composable
private fun AddRecords(
    defaultCurrency: String,
    categorization: Categorization,
    onSelectCategory: (Boolean) -> Unit,
    dispatch: (event: FinanceEvent) -> Unit,
) {
    val scrollState = rememberScrollState()
    var amount: String by rememberSaveable { mutableStateOf("") }
    var isIncome by rememberSaveable { mutableStateOf(true) }
    var notes by rememberSaveable { mutableStateOf("") }
    var subcategoryId by rememberSaveable { mutableIntStateOf(-1) }
    var date by rememberSaveable { mutableLongStateOf(LocalDate.now().toEpochDay() * 24 * 60 * 60 * 1000) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.dimensions16)
            .imePadding()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CwText(
                text = stringResource(R.string.txt_add_record),
                textType = TextType.TITLE
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close),
                tint = Color.White,
                modifier = Modifier
                    .clickable {

                    }
            )
        }
        AnimatedTab(
            tabsList = stringArrayResource(R.array.txt_record_types),
            onCLick = { index ->
                isIncome = index == 0
                dispatch(FinanceEvent.UpdateCategories(isIncome))
            }
        )
        AmountEditText(
            defaultCurrency = defaultCurrency,
            isIncome = isIncome,
            value = amount,
            onValueChange = {
                amount = it
            }
        )
        SelectCategory(
            categorization.category.name,
        ) {
            onSelectCategory(isIncome)
        }
        Subcategories(
            categorization.subcategories
        ) {
            subcategoryId = it
        }
        CalendarTextField(
            label = stringResource(R.string.date_time),
            onDateSelected = {
                date = it
            }
        )
        TransparentEditText(
            value = notes,
            label = stringResource(R.string.notes),
            hint = stringResource(R.string.add_notes_here),
            onValueChange = {
                notes = it
            }
        )
        CwButton(
            text = stringResource(R.string.add_records),
            isFullWidth = true
        ) {
            dispatch(
                FinanceEvent.SaveTransaction(
                    Transaction(
                        amount = amount.toDoubleOrNull() ?: 0.0,
                        type = if (isIncome) RecordTransactionType.CREDIT else RecordTransactionType.DEBIT,
                        notes = notes,
                        categoryId = categorization.category.id,
                        subcategoryId = subcategoryId,
                        date = date
                    )
                )
            )
        }
    }
}