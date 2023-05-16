package com.zerdasoftware.monthlycalendarwithevents.baseclass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<binding : ViewDataBinding> : Fragment() {

    abstract val layoutId: Int
    lateinit var binding: binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (::binding.isInitialized) { return binding.root }
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }
}