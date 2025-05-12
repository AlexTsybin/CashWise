package com.alextsy.common.model.feature.dashboard

import com.alextsy.common.model.charts.PieData
import com.alextsy.common.model.records.Transaction

data class DashboardData(
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val currentBalance: Double = 0.0,
    val recentTransactions: List<Transaction> = emptyList(),
    val pieInfo: List<PieData> = emptyList(),
)