package com.example.whitelabelexample.ui.cardinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.ext.*
import com.example.whitelabelexample.common.mvvm.getViewModel
import com.example.whitelabelexample.domain.models.Card
import com.example.whitelabelexample.ui.cardinfo.CardInfoViewModel.ScreenState.*
import com.example.whitelabelexample.ui.cardinfo.barcode.BarcodeEncoder
import com.example.whitelabelexample.ui.cardinfo.barcode.ZxingBarcodeEncoder
import com.redmadrobot.inputmask.helper.Mask
import com.redmadrobot.inputmask.model.CaretString
import kotlinx.android.synthetic.main.fragment_card_info.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardInfoFragment : Fragment() {

    private val viewModel: CardInfoViewModel by viewModel()

    private val barcodeEncoder: BarcodeEncoder by lazy { ZxingBarcodeEncoder() }

    private val cardNumberMask by lazy { Mask(viewModel.cardMask) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBar()
        setOnRefreshListener()
        observeScreenState()
    }

    private fun setStatusBar(){
        val isLightStatusBar = context!!.getBool(R.bool.is_light_status_bar_on_screens_with_background)
        activity?.setupStatusBarColor(R.color.background, isLightStatusBar)
    }

    private fun setOnRefreshListener() {
        card_info_content_swipe_refresh_container.setOnRefreshListener {
            card_info_content_swipe_refresh_container.isRefreshing = true
            viewModel.onRefreshClick()
        }
    }

    private fun observeScreenState() {
        viewModel.screenState.observe(this, Observer {
            when (it) {
                is Loading -> showLoading()
                is Content -> showContent(it.card)
            }
        })
    }

    private fun showLoading() {
        card_info_animator.visibleChildId = R.id.card_info_progress_bar
    }

    private fun showContent(card: Card) {
        setStatus(card.status)
        setDiscountAndBalance(card.discount, card.balance)
        setCardNumber(card.number)
        setBarcode(card.number)
        hideSwipeRefreshProgress()
        card_info_animator.visibleChildId = R.id.card_info_content_swipe_refresh_container
    }

    private fun setStatus(status: String?) {
        val isShowStatus = status != null && status.isNotBlank()
        card_info_status_label_text_view.setVisible(isShowStatus)
        card_info_status_value_text_view.setVisible(isShowStatus)
        if (isShowStatus)
            card_info_status_value_text_view.text = status
    }

    private fun setDiscountAndBalance(discount: Int?, balance: Int?) {
        discount?.let {
            showDiscount(it)
            showBalanceAtBalance(balance)
        } ?: showBalanceAtDiscount(balance)
    }

    private fun showDiscount(discount: Int?) {
        card_info_discount_text_view.setVisible(discount != null)
        discount?.let {
            card_info_discount_text_view.text = getString(R.string.card_info_discount_percent_text, it)
        }
    }

    private fun showBalanceAtBalance(balance: Int?) {
        card_info_balance_text_view.setVisible(balance != null)
        card_info_discount_bonuses_word_text_view.setVisible(false)
        balance?.let {
            card_info_balance_text_view.text = getString(
                R.string.card_info_balance_at_balance_text, balance, getBonusesWord(balance)
            )
        }
    }

    private fun showBalanceAtDiscount(balance: Int?) {
        val isShowBalance = balance != null
        card_info_discount_text_view.setVisible(isShowBalance)
        card_info_discount_bonuses_word_text_view.setVisible(isShowBalance)
        card_info_balance_text_view.setVisible(false)
        balance?.let {
            card_info_discount_text_view.text = "$balance"
            card_info_discount_bonuses_word_text_view.text = getBonusesWord(balance)
        }
    }

    private fun getBonusesWord(balance: Int) = context?.resources?.getQuantityString(
        R.plurals.card_info_bonuses_word, balance
    )

    private fun setCardNumber(cardNumber: String) {
        card_info_card_number_text_view.text = cardNumberMask.format(cardNumber)
    }

    private fun setBarcode(cardNumber: String) {
        card_info_barcode_image.onSizeCalculated { width, height ->
            try {
                val encodedBitmap = barcodeEncoder.encodeBitmap(cardNumber, viewModel.barcodeType, width, height)
                card_info_barcode_image.setImageBitmap(encodedBitmap)
            } catch (exception: Exception) {
                context?.showErrorAlertDialog(R.string.card_info_barcode_encoding_fail)
            }
        }
    }

    private fun hideSwipeRefreshProgress() {
        card_info_content_swipe_refresh_container.isRefreshing = false
    }
}

fun Mask.format(string: String) =
    apply(CaretString(string, 0), false).formattedText.string
