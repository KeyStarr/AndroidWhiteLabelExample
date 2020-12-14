package com.example.whitelabelexample.ui.getcard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.databinding.BaseBindingMvvmFragment
import com.example.whitelabelexample.common.ext.initBackButton
import com.example.whitelabelexample.common.ext.setupStatusBarColor
import com.example.whitelabelexample.databinding.FragmentGetCardBinding
import com.example.whitelabelexample.ui.getcard.GetCardViewModel.ScreenState.Content
import com.example.whitelabelexample.ui.getcard.GetCardViewModel.ScreenState.Loading
import kotlinx.android.synthetic.main.fragment_get_card.*
import org.koin.android.viewmodel.ext.android.viewModel

internal class GetCardFragment :
    BaseBindingMvvmFragment<GetCardViewModel, FragmentGetCardBinding>() {

    override val viewModel: GetCardViewModel by viewModel()

    override fun layoutId() = R.layout.fragment_get_card

    override fun setBindingVars(binding: FragmentGetCardBinding) {
        binding.viewmodel = viewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setupStatusBarColor(android.R.color.white, true)
        get_card_toolbar.initBackButton(activity)
        initRecyclerAdapter()
        observeScreenState()
    }

    private fun initRecyclerAdapter() {
        get_card_fields_recycler.initAdapter {
            viewModel.onFieldInputChanged(it)
        }
    }

    private fun observeScreenState() {
        viewModel.screenState.observe(this, Observer {
            when (it) {
                is Loading -> setLoadingState()
                is Content -> setContentState(it)
            }
        })
    }

    private fun setLoadingState() {
        toggleInput(false)
        toggleProgress(true)
    }

    private fun setContentState(state: Content) {
        toggleProgress(false)
        get_card_fields_recycler.swapItems(state.fields)
        toggleInput(true)
    }

    private fun toggleProgress(enable: Boolean) {
        get_card_progress_bar.isVisible = enable
    }

    private fun toggleInput(enable: Boolean) {
        get_card_fields_recycler.toggleInputAtChildren(enable)
    }
}
