package com.example.whitelabelexample.ui.loyalty.nocard

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.R
import com.example.whitelabelexample.ui.common.databinding.BaseBindingMvvmFragment
import com.example.whitelabelexample.ui.common.ext.getBool
import com.example.whitelabelexample.ui.common.ext.setupStatusBarColor
import com.example.whitelabelexample.databinding.FragmentNoCardBinding
import org.koin.android.viewmodel.ext.android.viewModel

class NoCardFragment : BaseBindingMvvmFragment<NoCardViewModel, FragmentNoCardBinding>() {

    override val viewModel: NoCardViewModel by viewModel()

    override fun layoutId() = R.layout.fragment_no_card

    override fun setBindingVars(binding: FragmentNoCardBinding) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBar()
    }

    private fun setStatusBar(){
        val isLightStatusBar = requireContext().getBool(R.bool.is_light_status_bar_on_screens_with_background)
        activity?.setupStatusBarColor(R.color.background, isLightStatusBar)
    }
}

@BindingAdapter(value = ["userId", "rationaleResId"])
fun formatUserId(textView: TextView, userId: MutableLiveData<String>, rationaleResId: Int) {
    userId.value?.let {
        val message = textView.context.getString(rationaleResId, it)
        textView.text = Html.fromHtml(message)
    }
}
