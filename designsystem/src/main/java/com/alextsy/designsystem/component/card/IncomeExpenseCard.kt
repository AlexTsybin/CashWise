package com.alextsy.designsystem.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alextsy.designsystem.theme.LocalDimensions
import com.alextsy.designsystem.theme.LocalWindowType
import com.alextsy.designsystem.theme.WindowType

@Composable
fun IncomeExpenseCard(
    income: List<Float>,
    expenses: List<Float>,
) {
    when (LocalWindowType.current) {
        WindowType.Compact -> {
            CwCard(
                modifier = Modifier.fillMaxWidth(),
            ) {
                FinanceCard(
                    expenses = income,
                    isIncome = true,
                )
            }
            CwCard(
                modifier = Modifier.fillMaxWidth(),
            ) {
                FinanceCard(
                    expenses = expenses,
                    isIncome = false,
                )
            }
        }
        else -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8),
            ) {
                CwCard(
                    modifier = Modifier.weight(1f),
                ) {
                    FinanceCard(
                        expenses = income,
                        isIncome = true,
                    )
                }
                CwCard(
                    modifier = Modifier.weight(1f),
                ) {
                    FinanceCard(
                        expenses = expenses,
                        isIncome = false,
                    )
                }
            }
        }
    }
}