package com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.widget.ViewPager2
import com.zerdasoftware.monthlycalendarwithevents.R
import com.zerdasoftware.monthlycalendarwithevents.baseclass.BaseFragment
import com.zerdasoftware.monthlycalendarwithevents.databinding.FragmentMonthlyBinding
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.*
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.CURRENT_MONTH_POSITION
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.CURRENT_YEAR
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.DAY_OF_MONTHLY_LIST
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.START_MONTH_POSITION
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.TUNER_YEAR
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Interface.SetOnClickDailyEventOfMonth
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.adapter.MonthlyViewPagerAdapter
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.model.MonthlyViewPagerModel

class MonthlyFragment : BaseFragment<FragmentMonthlyBinding>(), SetOnClickDailyEventOfMonth {
    override val layoutId: Int = R.layout.fragment_monthly

    /** INIT VIEWPAGER LIST & ADAPTER */
    private lateinit var monthlyViewPagerAdapter: MonthlyViewPagerAdapter
    private lateinit var viewPagerList: ArrayList<MonthlyViewPagerModel>
    companion object { var  myViewLifecycleOwner: LifecycleOwner?=null }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewLifecycleOwner = this

        setFirstViewPagerData()
        setFirstDayOfMonthlyData()
    }

    /** SET VIEWPAGER FIRST DATA */
    private fun setFirstViewPagerData() {
        /** SET FIRST VIEWPAGER DATA */
        viewPagerList = ArrayList()
        for (i in 1..12) {
            viewPagerList.add(MonthlyViewPagerModel(i + 1))
        }

        /** SETUP ADAPTER */
        monthlyViewPagerAdapter = MonthlyViewPagerAdapter(requireContext(), viewPagerList, this, -1)
        binding.fragmentMonthCalendarViewPager.adapter = monthlyViewPagerAdapter

        /** SETUP VIEWPAGER */
        binding.fragmentMonthCalendarViewPager.postDelayed({
            binding.fragmentMonthCalendarViewPager.setCurrentItem(START_MONTH_POSITION, false)
        }, 100)
        onInfinitePageChangeCallback(viewPagerList.size + 2)
    }

    /** SET DAY OF MONTH LIST */
    private fun setFirstDayOfMonthlyData() {
        DAY_OF_MONTHLY_LIST.value = ArrayList()
        val eventList = ArrayList<String>()
        monthlyViewPagerAdapter.setOnSliderViewPagerMonthlyData(
            setFirstDayOfMonthlyListData(DAY_OF_MONTHLY_LIST.value!!,lastMonthDay(),startMonthDay(requireContext()),eventList,requireContext())
        )
    }

    /** SLIDER VIEWPAGER CHANGE MONTH & YEAR */
    private fun onInfinitePageChangeCallback(listSize: Int) {
        binding.fragmentMonthCalendarViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (binding.fragmentMonthCalendarViewPager.currentItem) {
                        /** NEXT YEAR */ listSize - 1 -> { setPagerAndYear(1,1) }
                        /** LAST YEAR */ 0 -> { setPagerAndYear(listSize - 2,-1) }
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position in 1..12) { setMonthAndYearTitle(position) }
            }
        })
    }

    /** NEXT or LAST YEAR */
    private fun setPagerAndYear(item:Int,count:Int) {
        binding.fragmentMonthCalendarViewPager.setCurrentItem(item, false)
        TUNER_YEAR += count
    }

    /** SET MAIN ACTIVITY MONTH & YEAR TITLE */
    private fun setMonthAndYearTitle(position: Int) {
        CURRENT_MONTH_POSITION = position
        setMonthTitle(getMonthsArray(requireContext())[CURRENT_MONTH_POSITION - 1])
        setYearTitle((CURRENT_YEAR + TUNER_YEAR).toString())

        DAY_OF_MONTHLY_LIST.value!!.clear()
        val eventList = ArrayList<String>()
        monthlyViewPagerAdapter.setOnSliderViewPagerMonthlyData(
            setFirstDayOfMonthlyListData(DAY_OF_MONTHLY_LIST.value!!,lastMonthDay(),startMonthDay(requireContext()),eventList,requireContext())
        )
    }

    /** EVENT OF THE SELECTED DAY */
    override fun setDailyEvent(position: Int, day: String) {

    }

}