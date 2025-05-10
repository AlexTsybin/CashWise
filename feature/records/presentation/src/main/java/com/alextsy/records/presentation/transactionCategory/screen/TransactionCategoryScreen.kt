package com.alextsy.records.presentation.transactionCategory.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alextsy.common.model.records.Categorization
import com.alextsy.designsystem.component.text.CwText
import com.alextsy.designsystem.component.text.TextType
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.records.presentation.transactionCategory.mvi.TransactionCategoryEvent
import com.alextsy.records.presentation.transactionCategory.mvi.TransactionCategoryUiState
import com.alextsy.records.presentation.transactionCategory.mvi.TransactionCategoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectCategoryScreen(
    viewModel: TransactionCategoryViewModel = koinViewModel<TransactionCategoryViewModel>(),
    isIncome: Boolean,
    onCategorySelected: (Categorization) -> Unit,
    onBackPress: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.processEvent(TransactionCategoryEvent.Initialize(isIncome))
    }

    val state = viewModel.state.collectAsStateWithLifecycle()

    when (val uiState = state.value) {
        is TransactionCategoryUiState.CategoriesLoaded -> {
            Category(onBackPress, uiState.categories, onCategorySelected)
        }
        TransactionCategoryUiState.Initial -> LinearProgressIndicator()
    }
}

@Composable
private fun Category(
    onBackPress: () -> Unit,
    categories: List<Categorization>,
    onCategorySelected: (Categorization) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = LocalDimensions.current.dimensions16,
                vertical = LocalDimensions.current.dimensions8,
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions16),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        onBackPress()
                    },
            )
            CwText(
                text = "Select Category",
                textType = TextType.LABEL_LARGE,
            )
        }
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions8))
        LazyColumn {
            items(categories) {
                CategoryItemRow(it) {
                    onCategorySelected(it)
                }
            }
        }
    }
}

@Composable
fun CategoryItemRow(
    categorization: Categorization,
    onCategorySelected: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCategorySelected()
            }
            .padding(
                vertical = LocalDimensions.current.dimensions12,
                horizontal = LocalDimensions.current.dimensions8,
            ),
        verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions16),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(LocalDimensions.current.dimensions40)
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
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = categorization.category.icon,
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions16))
            CwText(
                text = categorization.category.name,
                textType = TextType.LABEL_LARGE,
            )
        }
    }
}