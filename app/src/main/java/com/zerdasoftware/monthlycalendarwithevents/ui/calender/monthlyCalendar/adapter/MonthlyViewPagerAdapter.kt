package com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zerdasoftware.monthlycalendarwithevents.R
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Constants.DAY_OF_MONTHLY_LIST
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Interface.SetOnClickDailyEventOfMonth
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Interface.SetOnSliderViewPagerMonthly
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.model.DayOfMonthlyModel
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.model.MonthlyViewPagerModel
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.screen.MonthlyFragment

class MonthlyViewPagerAdapter(
    private val context: Context,
    originalList: List<MonthlyViewPagerModel>,
    private val setOnClickDayOfMonthly: SetOnClickDailyEventOfMonth,
    private var oldPosition:Int
) : RecyclerView.Adapter<MonthlyViewPagerAdapter.InfiniteRecyclerViewHolder>(),
    SetOnSliderViewPagerMonthly {

    private val viewPagerDataList: List<MonthlyViewPagerModel> = listOf(originalList.last()) + originalList + listOf(originalList.first())
    private lateinit var dayOfMonthlyAdapter: DayOfMonthlyAdapter


    class InfiniteRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(dayOfMonthlyAdapter: DayOfMonthlyAdapter) {
            val monthViewPagerCalendarRecyclerView: RecyclerView = itemView.findViewById(R.id.monthlyViewPagerCalendarRecyclerView)

            monthViewPagerCalendarRecyclerView.apply {
                layoutManager = GridLayoutManager(context,7)
                setHasFixedSize(true)
            }


            DAY_OF_MONTHLY_LIST.observe(MonthlyFragment.myViewLifecycleOwner!!) {
                dayOfMonthlyAdapter.setData(it)
                monthViewPagerCalendarRecyclerView.adapter = dayOfMonthlyAdapter
            }
        }

        companion object {
            fun from(parent: ViewGroup) : InfiniteRecyclerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(R.layout.monthly_view_pager_layout,parent, false)
                return InfiniteRecyclerViewHolder(itemView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteRecyclerViewHolder {
        return InfiniteRecyclerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: InfiniteRecyclerViewHolder, position: Int) {
        dayOfMonthlyAdapter = DayOfMonthlyAdapter(context,setOnClickDayOfMonthly,oldPosition)
        holder.bind(dayOfMonthlyAdapter)
    }

    override fun getItemCount(): Int { return viewPagerDataList.size }

    override fun setOnSliderViewPagerMonthlyData(dayOfMonth: ArrayList<DayOfMonthlyModel>) { DAY_OF_MONTHLY_LIST.value = dayOfMonth }
}