package com.example.whitelabelexample.ui.enteruserid

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.models.UserIdType
import com.example.whitelabelexample.domain.usecase.ValidateUserIdUseCase
import com.example.whitelabelexample.domain.config.EnterUserIdConfig
import com.example.whitelabelexample.domain.usecase.LoginUseCase
import com.example.whitelabelexample.ui.getcard.GetCardViewModel
import com.example.whitelabelexample.ui.main.ProjectScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router


class EnterUserIdViewModel(
    private val configRep: EnterUserIdConfig,
    private val validateUserIdUseCase: ValidateUserIdUseCase,
    private val loginUseCase: LoginUseCase,
    private val router: Router
) : BaseViewModel() {

    private val registerMethod by lazy { configRep.userIdType() }
    private var lastCorrectUserId: String? = null

    val explanationResId by lazy {
        when (registerMethod) {
            UserIdType.PHONE -> R.string.enter_user_id_explanation_phone_number
            UserIdType.EMAIL -> R.string.enter_user_id_explanation_email
        }
    }
    val inputMethod by lazy { registerMethod.name }
    val userIdInputMask by lazy { configRep.userIdInputMask() }
    var isNextButtonEnabled = MutableLiveData<Boolean>().apply { value = false }

    fun onInputChange(maskFilled: Boolean, input: String) {
        if (!maskFilled) {
            isNextButtonEnabled.value = false
            return
        }
        val isInputCorrect = validateUserIdUseCase(input)
        if (isInputCorrect) lastCorrectUserId = input
        isNextButtonEnabled.value = isInputCorrect
    }

    fun onNextClick() {
        lastCorrectUserId?.let {
            launch {
                withContext(Dispatchers.IO) { loginUseCase(lastCorrectUserId!!) }
                val screen = ProjectScreen.NoCard()
                router.newRootScreen(screen)
            }
        }
    }
}
