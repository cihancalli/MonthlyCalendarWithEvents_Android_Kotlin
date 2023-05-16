package com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.zerdasoftware.monthlycalendarwithevents.R
import com.zerdasoftware.monthlycalendarwithevents.baseclass.convertLongToShortDay
import com.zerdasoftware.monthlycalendarwithevents.baseclass.convertShortDayId
import com.zerdasoftware.monthlycalendarwithevents.ui.activities.MainActivity
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.CALENDAR_DAY
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.CALENDAR_MONTH
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.CURRENT_MONTH_POSITION
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.DAY_DATE_MODEL
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.TUNER_YEAR
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.model.DayDateModel
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.model.DayOfMonthlyModel
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.screen.MonthlyEventFragment
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList

object Constants {

    /** GET LOCAL DATE NOW */
    @SuppressLint("NewApi")
    val CALENDAR_DAY = LocalDate.now().format(DateTimeFormatter.ofPattern("dd")).toInt()

    @SuppressLint("NewApi")
    val CALENDAR_MONTH = LocalDate.now().format(DateTimeFormatter.ofPattern("MM")).toInt()

    @SuppressLint("NewApi")
    val CALENDAR_YEAR = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")).toInt()

    /**  */
    var DAY_OF_MONTHLY_LIST = MutableLiveData<ArrayList<DayOfMonthlyModel>>()

    /**  */
    val DAY_DATE_MODEL = DayDateModel()

    /** MAIN ACTIVITY MONTH & YEAR TITLE */
    var MONTH_TITLE = MutableLiveData<String>()
    var YEAR_TITLE = MutableLiveData<String>()

    /** VIEWPAGER YEAR */
    var TUNER_YEAR = 0
    var CURRENT_YEAR = CALENDAR_YEAR

    /** VIEWPAGER MONTH */
    var START_MONTH_POSITION = CALENDAR_MONTH
    var CURRENT_MONTH_POSITION = START_MONTH_POSITION
}

/** GET STRING VALUE MONTHS ARRAY LIST */
fun getMonthsArray(context: Context): Array<String> {
    val resources: Resources = context.resources
    return resources.getStringArray(R.array.months_array)
}

/** SET MAIN ACTIVITY MONTH TITLE */
fun setMonthTitle(month: String) {
    val setMonth = MainActivity()
    setMonth.setMonthTitle(month)
}

/** SET MAIN ACTIVITY YEAR TITLE */
fun setYearTitle(year: String) {
    val setYear = MainActivity()
    setYear.setYearTitle(year)
}

fun setDailyEventOfMonth(event: ArrayList<String>){
    val setDailyEvent = MonthlyEventFragment()
    setDailyEvent.setEvent(event)
}

@SuppressLint("NewApi")
fun lastMonthDay(): Int {
    return DAY_DATE_MODEL.endDay(CURRENT_MONTH_POSITION - CALENDAR_MONTH, TUNER_YEAR).dayOfMonth.toInt()
}

fun startMonthDay(context: Context): Int {
    return convertShortDayId(
        convertLongToShortDay(
            DAY_DATE_MODEL.firstDay(
                CURRENT_MONTH_POSITION - CALENDAR_MONTH,
                TUNER_YEAR
            ).dayOfWeek.uppercase(), context
        ), context
    )
}


fun setFirstDayOfMonthlyListData(
    dayOfMonthList: ArrayList<DayOfMonthlyModel>, monthSize: Int, startSize: Int, eventList: ArrayList<String>, context: Context
): ArrayList<DayOfMonthlyModel>{
    val dayf = (CALENDAR_DAY - 1) % 7
    for (i in 0 until monthSize + startSize) {
        if (i < startSize) {
           addDay(dayOfMonthList,context," ",R.drawable.mc_day_back_default,R.drawable.circle_event_default,R.color.monthly_first_text_color)
        }else{
            addDay(dayOfMonthList,context,"${i - startSize + 1}",R.drawable.mc_day_back_default,R.drawable.circle_event_default,R.color.monthly_default_text_color)
        }
    }
    return dayOfMonthList
}

@SuppressLint("ResourceType")
fun addDay(dayOfMonthList: ArrayList<DayOfMonthlyModel>, context: Context, day: String, status: Int, event: Int, text: Int) {
    dayOfMonthList.add(DayOfMonthlyModel(day, status, event, context.getString(text)))
}





