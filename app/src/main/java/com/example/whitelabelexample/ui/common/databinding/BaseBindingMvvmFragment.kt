package com.example.whitelabelexample.ui.common.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseBindingMvvmFragment<ViewModel, DataBinding : ViewDataBinding> : Fragment() {

    protected abstract val viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<DataBinding>(inflater, layoutId(), container, false)
        binding.lifecycleOwner = this
        setBindingVars(binding)
        return binding.root
    }

    protected abstract fun layoutId(): Int

    protected open fun setBindingVars(binding: DataBinding){
        // pass
    }
}