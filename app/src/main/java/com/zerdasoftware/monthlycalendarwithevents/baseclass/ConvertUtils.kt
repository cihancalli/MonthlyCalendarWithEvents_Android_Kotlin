package com.zerdasoftware.monthlycalendarwithevents.baseclass

import android.content.Context
import com.zerdasoftware.monthlycalendarwithevents.R

/** CALENDAR */
fun convertLongToShortDay(day: String, context: Context): String {
    return when (day) {
        context.getString(R.string.monday)      -> { context.getString(R.string.mon) }
        context.getString(R.string.tuesday)     -> { context.getString(R.string.tue) }
        context.getString(R.string.wednesday)   -> { context.getString(R.string.wed) }
        context.getString(R.string.thursday)    -> { context.getString(R.string.thu) }
        context.getString(R.string.friday)      -> { context.getString(R.string.fri) }
        context.getString(R.string.saturday)    -> { context.getString(R.string.sat) }
        context.getString(R.string.sunday)      -> { context.getString(R.string.sun) }
        else                                    -> { " " }
    }
}

fun convertShortDayId(day: String, context: Context): Int {
    return when (day) {
        context.getString(R.string.mon) -> { 0 }
        context.getString(R.string.tue) -> { 1 }
        context.getString(R.string.wed) -> { 2 }
        context.getString(R.string.thu) -> { 3 }
        context.getString(R.string.fri) -> { 4 }
        context.getString(R.string.sat) -> { 5 }
        context.getString(R.string.sun) -> { 6 }
        else                            -> { 0 }
    }
}

fun convertZeroToNineWithZero(day: String): String {
    return when (day) {
        "0" -> { "00" }
        "1" -> { "01" }
        "2" -> { "02" }
        "3" -> { "03" }
        "4" -> { "04" }
        "5" -> { "05" }
        "6" -> { "06" }
        "7" -> { "07" }
        "8" -> { "08" }
        "9" -> { "09" }
        else -> { day }
    }
}

fun convertMonthIdWithZero(month: String, context: Context): String {
    return when (month) {
        "0"                                     -> { "00" }
        context.getString(R.string.january)     -> { "01" }
        context.getString(R.string.february)    -> { "02" }
        context.getString(R.string.march)       -> { "03" }
        context.getString(R.string.april)       -> { "04" }
        context.getString(R.string.may)         -> { "05" }
        context.getString(R.string.june)        -> { "06" }
        context.getString(R.string.july)        -> { "07" }
        context.getString(R.string.august)      -> { "08" }
        context.getString(R.string.september)   -> { "09" }
        context.getString(R.string.october)     -> { "10" }
        context.getString(R.string.november)    -> { "11" }
        context.getString(R.string.december)    -> { "12" }
        else                                    -> { "00" }
    }
}

