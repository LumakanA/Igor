package ru.handh.school.igor.ui.screen.signin

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.handh.school.igor.domain.SignInRequest
import ru.handh.school.igor.domain.SignInUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class SignInViewModel :
    BaseViewModel<SignInState, SignInViewAction>(InitialSignInState) {
    private val signInUseCase = SignInUseCase()
    override fun onAction(action: SignInViewAction) =
        when (action) {
            SignInViewAction.SubmitClicked -> onSubmitClicked()
            is SignInViewAction.UpdateEmail -> onUpdateEmail(action.email)
        }

    private fun onSubmitClicked() {
        viewModelScope.launch {
            signInUseCase.execute(SignInRequest(state.value.email))
        }
    }

    private fun onUpdateEmail(email: String) = reduceState {
        it.copy(email = email)
    }
}
