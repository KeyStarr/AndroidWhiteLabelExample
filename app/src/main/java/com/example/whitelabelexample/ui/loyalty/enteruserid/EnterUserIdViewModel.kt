package com.example.whitelabelexample.ui.loyalty.enteruserid

import androidx.lifecycle.MutableLiveData
import com.example.whitelabelexample.R
import com.example.whitelabelexample.common.mvvm.BaseViewModel
import com.example.whitelabelexample.domain.config.AuthConfig
import com.example.whitelabelexample.domain.models.UserIdType
import com.example.whitelabelexample.domain.usecase.auth.GetUserIdParamsUseCase
import com.example.whitelabelexample.domain.usecase.auth.ValidateUserIdUseCase
import com.example.whitelabelexample.domain.usecase.auth.LoginUseCase
import com.example.whitelabelexample.ui.main.ProjectScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.terrakok.cicerone.Router


class EnterUserIdViewModel(
    private val validateUserIdUseCase: ValidateUserIdUseCase,
    private val getUserIdParamsUseCase: GetUserIdParamsUseCase,
    private val loginUseCase: LoginUseCase,
    private val router: Router
) : BaseViewModel() {

    private val userIdParams by lazy { getUserIdParamsUseCase() }
    private var lastCorrectUserId: String? = null

    val explanationResId by lazy {
        when (userIdParams.type) {
            UserIdType.PHONE -> R.string.enter_user_id_explanation_phone_number
            UserIdType.EMAIL -> R.string.enter_user_id_explanation_email
        }
    }
    val inputMethod by lazy { userIdParams.type.name }
    val userIdInputMask by lazy { userIdParams.mask }
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
