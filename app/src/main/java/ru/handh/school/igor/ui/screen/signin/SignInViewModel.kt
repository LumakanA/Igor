package ru.handh.school.igor.ui.screen.signin

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.handh.school.igor.domain.session.SessionResponse
import ru.handh.school.igor.domain.session.SessionUseCase
import ru.handh.school.igor.domain.signin.SignInRequest
import ru.handh.school.igor.domain.signin.SignInUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class SignInViewModel :
    BaseViewModel<SignInState, SignInViewAction>(InitialSignInState) {
    private val signInUseCase = SignInUseCase()
    private val sessionUseCase = SessionUseCase()

    override fun onAction(action: SignInViewAction) =
        when (action) {
            SignInViewAction.SubmitClicked -> onSubmitClicked()
            is SignInViewAction.SubmitClickedCode -> onSubmitClickedCode()
            is SignInViewAction.UpdateEmail -> onUpdateEmail(action.email)
            is SignInViewAction.UpdateVerificationCode -> onUpdateVerificationCode(action.code)
        }

    private fun onSubmitClicked() {
        viewModelScope.launch {
            try {
                signInUseCase.execute(SignInRequest(state.value.email))
                reduceState {
                    it.copy(showVerificationCodeInput = true)
                }
            } catch (e: Exception) {
                handleSignInError(e)
            }
        }
    }

    private fun onSubmitClickedCode() {
        viewModelScope.launch {
            try {
                sessionUseCase.execute(SessionResponse(state.value.code))
                Log.d("qwerty", "send_code")
            } catch (e: Exception) {
                handleSignInError(e)
            }
        }
    }

    private fun onUpdateEmail(email: String) = reduceState {
        it.copy(email = email)
    }

    private fun onUpdateVerificationCode(code: String) = reduceState {
        it.copy(code = code)
    }

    private fun handleSignInError(exception: Exception) {
        println("Sign In Error: ${exception.message}")
    }
}
