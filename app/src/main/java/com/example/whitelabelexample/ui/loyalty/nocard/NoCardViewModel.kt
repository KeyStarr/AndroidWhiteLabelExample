package com.example.whitelabelexample.ui.loyalty.nocard

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.CardConfig
import com.example.whitelabelexample.domain.config.UserIdConfig
import com.example.whitelabelexample.ui.loyalty.cardinfo.format
import com.example.whitelabelexample.domain.repositories.storage.UserIdStorageRepository
import com.example.whitelabelexample.domain.models.UserIdType
import com.example.whitelabelexample.domain.models.ObtainCardMethod
import com.example.whitelabelexample.ui.main.ProjectScreen
import com.redmadrobot.inputmask.helper.Mask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router

class NoCardViewModel(
    private val cardConfig: CardConfig,
    private val userIdConfig: UserIdConfig,
    private val userIdRep: UserIdStorageRepository,
    private val router: Router
) : BaseViewModel() {

    private val cardObtainMethods by lazy { cardConfig.obtainmentMethods() }

    val userId = MutableLiveData<String>()

    val isShowGetVirtualButton by lazy {
        cardObtainMethods.contains(ObtainCardMethod.GENERATE_VIRTUAL)
    }
    val isShowBindPlasticButton by lazy {
        cardObtainMethods.contains(ObtainCardMethod.BIND_PHYSICAL)
    }

    val rationaleMsgResId by lazy {
        when (userIdConfig.type()) {
            UserIdType.PHONE -> R.string.no_card_for_phone_number_text
            UserIdType.EMAIL -> R.string.no_card_for_email_number_text
        }
    }

    init {
        launch {
            val rawId = withContext(Dispatchers.IO) { userIdRep.get() }
            rawId?.let { userId.value = formatUserId(it) }
        }
    }

    private fun formatUserId(value: String) =
        userIdConfig.mask()?.let { Mask(it).format(value) } ?: value

    fun onBindCardClick() {
        val screen = ProjectScreen.BindCard()
        router.navigateTo(screen)
    }

    fun onGetVirtualCardClick() {
        val screen = ProjectScreen.GetCard()
        router.navigateTo(screen)
    }
}
