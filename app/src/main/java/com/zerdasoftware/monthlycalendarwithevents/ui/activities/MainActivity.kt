package com.zerdasoftware.monthlycalendarwithevents.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zerdasoftware.monthlycalendarwithevents.databinding.ActivityMainBinding
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.MONTH_TITLE
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.YEAR_TITLE
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Interface.SetMonthAndYearTitle

class MainActivity : AppCompatActivity(), SetMonthAndYearTitle {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        setTitle()
    }

    /** SET BINDING DATA */
    private fun setBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    /** SET MONTH & YEAR TITLE */
    private fun setTitle() {
        MONTH_TITLE.observe(this){ binding.monthTitleTextView.text = it }
        YEAR_TITLE.observe(this){ binding.yearTitleTextView.text  = it }
    }

    /** GET MONTH & YEAR TITLE FROM MONTHLY FRAGMENT */
    override fun setMonthTitle(month: String) { MONTH_TITLE.value = month }
    override fun setYearTitle(year: String) { YEAR_TITLE.value = year }
}