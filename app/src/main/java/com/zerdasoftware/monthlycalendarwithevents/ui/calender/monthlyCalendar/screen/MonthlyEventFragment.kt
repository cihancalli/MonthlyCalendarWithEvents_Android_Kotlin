package com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.screen

import com.zerdasoftware.monthlycalendarwithevents.R
import com.zerdasoftware.monthlycalendarwithevents.baseclass.BaseFragment
import com.zerdasoftware.monthlycalendarwithevents.databinding.FragmentMonthlyEventBinding
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Interface.SetOnClickDailyEvent


class MonthlyEventFragment : BaseFragment<FragmentMonthlyEventBinding>(), SetOnClickDailyEvent {
    override val layoutId: Int = R.layout.fragment_monthly_event

    /** SET EVENT LIST */
    override fun setEvent(event: ArrayList<String>) {

    }


}