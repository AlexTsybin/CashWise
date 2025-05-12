package com.alextsy.common.model.charts

data class MonthlyData(
    val label: String,
    val income: Int,
    val expense: Int,
)

data class PieData(
    val name: String,
    val icon: String,
    val color: String,
    val amount: Double,
)

data class CircleGraphData(
    val value: Float,
    val valueLabel: String,
    val valueColor: String,
)

data class ProgressBarData(
    val value: Float,
    val valueLabel: String,
    val valueColor: String,
)

data class IncomeExpenseData(
    val total: Double = 0.0,
    val graphData: List<Float> = emptyList(),
)