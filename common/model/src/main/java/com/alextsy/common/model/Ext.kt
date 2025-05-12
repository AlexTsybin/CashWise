package com.alextsy.common.model

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Number.formatAsCurrency(): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.minimumFractionDigits = 1
    return format.format(this)
}

enum class DateFormatPattern(val pattern: String) {
    SHORT("MMM, yy"),
    LONG("dd MMM yyyy"),
}

fun Long.toDateFormat(format: DateFormatPattern): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(format.pattern, Locale.US)
    return formatter.format(date)
}