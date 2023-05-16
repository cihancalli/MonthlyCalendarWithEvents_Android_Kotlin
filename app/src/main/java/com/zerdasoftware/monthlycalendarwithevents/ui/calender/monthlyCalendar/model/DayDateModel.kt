package com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.model

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class DayDateModel (
    var hour: String="",
    var min: String="",
    var dayOfWeek: String="",
    var dayOfMonth: String="",
    var month: String="",
    var year: String="",
    var timeZone: String="")
{

    @SuppressLint("NewApi")
    private fun currentDay(tunerMonth:Int, tunerYear:Int):DayDateModel{
        return DayDateModel(
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY).toString(),

            Calendar.getInstance().get(Calendar.MINUTE).toString(),

            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).format(
                DateTimeFormatter.ofPattern("eeee")),

            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).format(
                DateTimeFormatter.ofPattern("dd")),

            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).format(
                DateTimeFormatter.ofPattern("MMMM")),

            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).format(
                DateTimeFormatter.ofPattern("yyyy")),

            ZonedDateTime.now().zone.toString()
        )
    }

    @SuppressLint("NewApi")
    fun selectedDay(tunerDay:Int, tunerMonth:Int, tunerYear:Int):DayDateModel{
        return DayDateModel(
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY).toString(),

            Calendar.getInstance().get(Calendar.MINUTE).toString(),

            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).plusDays(tunerDay.toLong()).format(
                DateTimeFormatter.ofPattern("eeee")),

            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).plusDays(tunerDay.toLong()).format(
                DateTimeFormatter.ofPattern("dd")),

            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).plusDays(tunerDay.toLong()).format(
                DateTimeFormatter.ofPattern("MMMM")),

            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).plusDays(tunerDay.toLong()).format(
                DateTimeFormatter.ofPattern("yyyy")),

            ZonedDateTime.now().zone.toString()
        )
    }

    @SuppressLint("NewApi")
    fun firstDay(tunerMonth:Int, tunerYear:Int):DayDateModel{
        val currentDayOfMonth:Long = (currentDay(tunerMonth,tunerYear).dayOfMonth.toInt()-1).toLong()
        return DayDateModel(
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY).toString(),
            Calendar.getInstance().get(Calendar.MINUTE).toString(),
            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).minusDays(currentDayOfMonth).format(
                DateTimeFormatter.ofPattern("eeee")),
            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).minusDays(currentDayOfMonth).format(
                DateTimeFormatter.ofPattern("dd")),
            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).minusDays(currentDayOfMonth).format(
                DateTimeFormatter.ofPattern("MMMM")),
            LocalDate.now().plusYears(tunerYear.toLong()).plusMonths(tunerMonth.toLong()).minusDays(currentDayOfMonth).format(
                DateTimeFormatter.ofPattern("yyyy")),
            ZonedDateTime.now().zone.toString()
        )
    }

    @SuppressLint("NewApi")
    fun endDay(tunerMonth:Int, tunerY:Int):DayDateModel{
        val currentDayOfMonth:Long = (currentDay(tunerMonth,tunerY).dayOfMonth.toInt()).toLong()
        return DayDateModel(
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY).toString(),
            Calendar.getInstance().get(Calendar.MINUTE).toString(),
            LocalDate.now().plusYears(tunerY.toLong()).plusMonths(tunerMonth.toLong()+1).minusDays(currentDayOfMonth).format(
                DateTimeFormatter.ofPattern("eeee")),
            LocalDate.now().plusYears(tunerY.toLong()).plusMonths(tunerMonth.toLong()+1).minusDays(currentDayOfMonth).format(
                DateTimeFormatter.ofPattern("dd")),
            LocalDate.now().plusYears(tunerY.toLong()).plusMonths(tunerMonth.toLong()+1).minusDays(currentDayOfMonth).format(
                DateTimeFormatter.ofPattern("MMMM")),
            LocalDate.now().plusYears(tunerY.toLong()).plusMonths(tunerMonth.toLong()+1).minusDays(currentDayOfMonth).format(
                DateTimeFormatter.ofPattern("yyyy")),
            ZonedDateTime.now().zone.toString()
        )
    }

    private fun getColId(dayWeekName: String, dayName: ArrayList<String>):Int {

        for (i in 0..6){
            if (dayName.get(i) == dayWeekName){
                return i
            }
        }
        return 0
    }

    fun getRowId(r0:String,r1:String,d:Int,dayName: ArrayList<String>):Int{
        val fd = 6 - getColId(r0,dayName)
        val ed = getColId(r1,dayName)
        val fed = fd + ed
        return ((d-fed-2)/7)+1
    }
}