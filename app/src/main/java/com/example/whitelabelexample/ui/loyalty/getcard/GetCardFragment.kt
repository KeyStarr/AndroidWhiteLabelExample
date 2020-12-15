package com.example.whitelabelexample.ui.loyalty.getcard

import android.os.Bundle
import android.view.View
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.databinding.BaseBindingMvvmFragment
import com.example.whitelabelexample.common.ext.initBackButton
import com.example.whitelabelexample.common.ext.setupStatusBarColor
import com.example.whitelabelexample.databinding.FragmentGetCardBinding
import kotlinx.android.synthetic.main.fragment_get_card.*
import org.koin.android.viewmodel.ext.android.viewModel

class GetCardFragment :
    BaseBindingMvvmFragment<GetCardViewModel, FragmentGetCardBinding>() {

    override val viewModel: GetCardViewModel by viewModel()

    override fun layoutId() = R.layout.fragment_get_card

    override fun setBindingVars(binding: FragmentGetCardBinding) {
        binding.viewModel = viewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setupStatusBarColor(android.R.color.white, true)
        get_card_toolbar.initBackButton(activity)
        initRecyclerAdapter()
        observeScreenState()
    }

    private fun initRecyclerAdapter() {
        get_card_fields_recycler.initAdapter(viewModel::onFieldInputChanged)
    }

    private fun observeScreenState() {
        viewModel.fieldsData.observe(viewLifecycleOwner, get_card_fields_recycler::swapItems)
    }
}
