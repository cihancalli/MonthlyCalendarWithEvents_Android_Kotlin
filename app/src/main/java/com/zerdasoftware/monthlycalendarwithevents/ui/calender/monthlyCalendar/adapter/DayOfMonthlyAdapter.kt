package com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zerdasoftware.monthlycalendarwithevents.R
import com.zerdasoftware.monthlycalendarwithevents.baseclass.AdapterDiffUtil
import com.zerdasoftware.monthlycalendarwithevents.databinding.DayOfMonthlyLayoutBinding
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.Interface.SetOnClickDailyEventOfMonth
import com.zerdasoftware.monthlycalendarwithevents.ui.calender.monthlyCalendar.model.DayOfMonthlyModel

class DayOfMonthlyAdapter(
    private val context: Context,
    private val setOnClickDayOfMonth: SetOnClickDailyEventOfMonth,
    private var oldPosition: Int
) : RecyclerView.Adapter<DayOfMonthlyAdapter.ViewHolder>() {

    private var dayOfMonthList = emptyList<DayOfMonthlyModel>()
    private var contentStatusColor = 0
    private var eventStatusColor = 0
    private var textStatusColor = ""

    inner class ViewHolder(val binding: DayOfMonthlyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val dayOfMonthLayoutContent = binding.dayOfMonthLayoutContent
        val dayOfMonthDayTextView = binding.dayOfMonthDayTextView
        val dayOfMonthDayEventImageView = binding.dayOfMonthDayEventImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DayOfMonthlyLayoutBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val modelClass = dayOfMonthList[position]
        holder.dayOfMonthLayoutContent.setBackgroundResource(modelClass.contentStatusColor!!)
        holder.dayOfMonthDayEventImageView.setImageResource(modelClass.eventStatusColor!!)
        holder.dayOfMonthDayTextView.text = modelClass.day
        holder.dayOfMonthDayTextView.setTextColor(Color.parseColor(modelClass.textStatusColor))

        if (modelClass.textStatusColor == context.getString(R.color.monthly_text_color) || modelClass.textStatusColor == context.getString(
                R.color.monthly_default_text_color) || modelClass.contentStatusColor == R.drawable.mc_day_back_selected) {

            holder.dayOfMonthLayoutContent.setOnClickListener {
                if (oldPosition != position) {

                    //eski tıklanan günü null değilse eski haline getir
                    if (oldPosition != -1) {
                        dayOfMonthList[oldPosition].contentStatusColor = contentStatusColor
                        dayOfMonthList[oldPosition].eventStatusColor = eventStatusColor
                        dayOfMonthList[oldPosition].textStatusColor = textStatusColor
                    }

                    //Yeni tıklanan günü olda kaydet
                    contentStatusColor = modelClass.contentStatusColor!!
                    eventStatusColor = modelClass.eventStatusColor!!
                    textStatusColor = modelClass.textStatusColor!!

                    //yeni tıklanan günü yeni haline getir
                    modelClass.contentStatusColor =
                        R.drawable.mc_day_back_selected
                    modelClass.textStatusColor = context.getString(R.color.monthly_default_text_color)

                    //tıklanan günün eventi varsa
                    if (modelClass.isEvent!!) {
                        modelClass.eventStatusColor = R.drawable.circle_event_selected
                    } else {
                        modelClass.eventStatusColor = R.drawable.circle_event_selected
                    }

                    setOnClickDayOfMonth.setDailyEvent(position, modelClass.day!!)
                    notifyDataSetChanged()
                }
                //yeni tıklanan günü olda kaydet
                oldPosition = position
            }
        }
    }

    override fun getItemCount(): Int = dayOfMonthList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setData(newDayOfMonthList: ArrayList<DayOfMonthlyModel>) {
        val diffUtil = AdapterDiffUtil(dayOfMonthList, newDayOfMonthList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        dayOfMonthList = newDayOfMonthList
        diffResults.dispatchUpdatesTo(this)
    }
}