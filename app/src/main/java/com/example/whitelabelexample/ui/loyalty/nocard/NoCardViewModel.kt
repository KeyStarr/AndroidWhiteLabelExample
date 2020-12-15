package com.example.whitelabelexample.ui.loyalty.nocard

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.ext.format
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.models.UserIdType
import com.example.whitelabelexample.domain.models.ObtainCardMethod
import com.example.whitelabelexample.domain.usecase.auth.GetUserIdParamsUseCase
import com.example.whitelabelexample.domain.usecase.auth.GetUserIdUseCase
import com.example.whitelabelexample.domain.usecase.card.GetObtainMethodsUseCase
import com.example.whitelabelexample.ui.main.ProjectScreen
import com.redmadrobot.inputmask.helper.Mask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router

class NoCardViewModel(
    private val getUserIdParamsUseCase: GetUserIdParamsUseCase,
    private val getObtainMethodsUseCase: GetObtainMethodsUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val router: Router
) : BaseViewModel() {

    private val userIdParams by lazy { getUserIdParamsUseCase() }
    private val cardObtainMethods by lazy { getObtainMethodsUseCase() }

    val isShowGetVirtualButton by lazy {
        cardObtainMethods.contains(ObtainCardMethod.GENERATE_VIRTUAL)
    }
    val isShowBindPlasticButton by lazy {
        cardObtainMethods.contains(ObtainCardMethod.BIND_PHYSICAL)
    }
    val rationaleMsgResId by lazy {
        when (userIdParams.type) {
            UserIdType.PHONE -> R.string.no_card_for_phone_number_text
            UserIdType.EMAIL -> R.string.no_card_for_email_number_text
        }
    }

    val userId = MutableLiveData<String>()

    init {
        launch {
            val rawId = withContext(Dispatchers.IO) { getUserIdUseCase() }
            rawId?.let { userId.value = formatUserId(it) }
        }
    }

    private fun formatUserId(value: String) =
        userIdParams.mask?.let { Mask(it).format(value) } ?: value

    fun onBindCardClick() {
        val screen = ProjectScreen.BindCard()
        router.navigateTo(screen)
    }

    fun onGetVirtualCardClick() {
        val screen = ProjectScreen.GetCard()
        router.navigateTo(screen)
    }
}
