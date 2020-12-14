package com.example.whitelabelexample.ui.bindcard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.databinding.BaseBindingMvvmFragment
import com.example.whitelabelexample.common.ext.hideKeyboard
import com.example.whitelabelexample.common.ext.initBackButton
import com.example.whitelabelexample.common.ext.onEndCallListener
import com.example.whitelabelexample.common.ext.setupStatusBarColor
import com.example.whitelabelexample.databinding.FragmentBindCardBinding
import com.example.whitelabelexample.ui.bindcard.BindCardViewModel.ScreenState.*
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.fragment_bind_card.*
import org.koin.android.viewmodel.ext.android.viewModel

class BindCardFragment : BaseBindingMvvmFragment<BindCardViewModel, FragmentBindCardBinding>() {

    override val viewModel : BindCardViewModel by viewModel()

    override fun layoutId() = R.layout.fragment_bind_card

    override fun setBindingVars(binding: FragmentBindCardBinding) {
        binding.viewmodel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setupStatusBarColor(android.R.color.white, true)
        bind_card_toolbar.initBackButton(activity)
        setInputMask()
        observeScreenState()
        bind_card_edit_text.onEndCallListener { viewModel.onNextButtonClick() }
    }

    private fun setInputMask() {
        with(bind_card_edit_text) {
            val inputMaskListener = MaskedTextChangedListener(
                viewModel.cardNumberInputMask, this, InputChangeListener()
            )
            addTextChangedListener(inputMaskListener)
            onFocusChangeListener = inputMaskListener
        }
    }

    private fun observeScreenState() {
        viewModel.screenState.observe(this, Observer {
            when (it) {
                ReadyInput -> setReadyInput()
                Loading -> setLoading()
            }
        })
    }

    private fun setReadyInput() {
        bind_card_input_layout.error = null
        toggleProgress(false)
        toggleInput(true)
    }

    private fun setLoading() {
        bind_card_edit_text.hideKeyboard()
        toggleInput(false)
        toggleProgress(true)
    }

    private fun toggleInput(enabled: Boolean) {
        bind_card_edit_text.isEnabled = enabled
        bind_card_next_button.isEnabled =
            if (!enabled) false
            else viewModel.isNextButtonEnabled.value!!
    }

    private fun toggleProgress(enabled: Boolean) {
        bind_card_progress_bar.isVisible = enabled
    }

    private inner class InputChangeListener : MaskedTextChangedListener.ValueListener {
        override fun onTextChanged(maskFilled: Boolean, extractedValue: String, formattedValue: String) {
            viewModel.onInputChange(maskFilled, extractedValue)
        }
    }
}
