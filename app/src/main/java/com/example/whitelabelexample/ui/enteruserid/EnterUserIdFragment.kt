package com.example.whitelabelexample.ui.enteruserid

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.databinding.BaseBindingMvvmFragment
import com.example.whitelabelexample.common.ext.getBool
import com.example.whitelabelexample.common.ext.hideKeyboard
import com.example.whitelabelexample.common.ext.onEndCallListener
import com.example.whitelabelexample.common.ext.setupStatusBarColor
import com.example.whitelabelexample.databinding.FragmentEnterUserIdBinding
import com.example.whitelabelexample.domain.models.UserIdType
import com.example.whitelabelexample.ui.enteruserid.EnterUserIdViewModel.ScreenState.Input
import com.example.whitelabelexample.ui.enteruserid.EnterUserIdViewModel.ScreenState.Loading
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.fragment_enter_user_id.*
import org.koin.android.viewmodel.ext.android.viewModel


internal class EnterUserIdFragment :
    BaseBindingMvvmFragment<EnterUserIdViewModel, FragmentEnterUserIdBinding>() {

    override val viewModel: EnterUserIdViewModel by viewModel()

    override fun layoutId() = R.layout.fragment_enter_user_id

    override fun setBindingVars(binding: FragmentEnterUserIdBinding) {
        binding.viewmodel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBar()
        setInputMaskAndChangeListener(viewModel.userIdInputMask)
        setOnEnterNextListener()
        observeScreenState()
    }

    private fun setStatusBar() {
        val isLightStatusBar = requireContext().getBool(R.bool.is_light_status_bar_on_screens_with_background)
        activity?.setupStatusBarColor(R.color.background, isLightStatusBar)
    }

    private fun setInputMaskAndChangeListener(mask: String?) {
        with(enter_user_id_input_edit_text) {
            if (mask == null) {
                setDefaultInputChangeListener()
                return
            }

            val inputMaskListener = MaskedTextChangedListener(mask, this, InputChangeListener())
            addTextChangedListener(inputMaskListener)
            onFocusChangeListener = inputMaskListener
        }
    }

    private fun setDefaultInputChangeListener() {
        enter_user_id_input_edit_text.addTextChangedListener {
            viewModel.onInputChange(true, it.toString())
        }
    }

    private fun setOnEnterNextListener(){
        enter_user_id_input_edit_text.onEndCallListener {
            if (enter_user_id_next_button.isEnabled)
                viewModel.onNextClick()
        }
    }

    private fun observeScreenState() {
        viewModel.screenState.observe(this, Observer {
            when (it) {
                is Loading -> setLoadingState()
                is Input -> setInputState()
            }
        })
    }

    private fun setLoadingState() {
        enter_user_id_input_edit_text.hideKeyboard()
        toggleInput(false)
        toggleProgress(true)
    }

    private fun setInputState() {
        toggleProgress(false)
        toggleInput(true)
    }

    private fun toggleInput(enable: Boolean) {
        enter_user_id_input_edit_text.isEnabled = enable
        enter_user_id_next_button.isEnabled = enable
    }

    private fun toggleProgress(show: Boolean) {
        enter_user_id_progress_bar.isVisible = show
    }

    private inner class InputChangeListener : MaskedTextChangedListener.ValueListener {
        override fun onTextChanged(
            maskFilled: Boolean,
            extractedValue: String,
            formattedValue: String
        ) {
            viewModel.onInputChange(maskFilled, extractedValue)
        }
    }
}

@BindingAdapter("userIdType")
fun EditText.setInputType(type: String) {
    val method = UserIdType.valueOf(type)
    val inputType = when (method) {
        UserIdType.PHONE -> InputType.TYPE_CLASS_PHONE
        UserIdType.EMAIL -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }
    setInputType(inputType)
}
