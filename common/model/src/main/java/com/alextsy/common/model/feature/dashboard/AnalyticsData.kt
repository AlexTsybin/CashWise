package com.alextsy.common.model.feature.dashboard

import com.alextsy.common.model.charts.CircleGraphData
import com.alextsy.common.model.charts.IncomeExpenseData
import com.alextsy.common.model.charts.MonthlyData
import com.alextsy.common.model.charts.ProgressBarData

data class AnalyticsData(
    val incomeData: IncomeExpenseData = IncomeExpenseData(),
    val expenseData: IncomeExpenseData = IncomeExpenseData(),
    val monthlyData: List<MonthlyData> = emptyList(),
    val circleIncomeGraphData: Map<String, List<CircleGraphData>> = emptyMap(),
    val barGraphData: Map<String, List<ProgressBarData>> = emptyMap(),
)