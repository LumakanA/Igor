package ru.handh.school.igor.ui.screen.signin

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.handh.school.igor.domain.Result
import ru.handh.school.igor.domain.session.SessionUseCase
import ru.handh.school.igor.domain.signin.SignInUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val sessionUseCase: SessionUseCase
) :
    BaseViewModel<SignInState, SignInViewAction>(InitialSignInState) {
    private val resultChannel = Channel<Result<Unit>>()
    val codeResult = resultChannel.receiveAsFlow()

    override fun onAction(action: SignInViewAction) =
        when (action) {
            is SignInViewAction.SubmitClicked -> onSubmitClicked()
            is SignInViewAction.SubmitClickedCode -> onSubmitClickedCode()
            is SignInViewAction.UpdateEmail -> onUpdateEmail(action.email)
            is SignInViewAction.UpdateVerificationCode -> onUpdateVerificationCode(action.code)
        }

    private fun onSubmitClicked() {
        viewModelScope.launch {
            val email = state.value.email
            if (emailValidation(email)) {
                reduceState {
                    it.copy(buttonEnabled = true)
                }
                try {
                    reduceState {
                        it.copy(showVerificationCodeInput = false, signInLoading = true)
                    }
                    val resultSignIn = signInUseCase.execute(email)
                    if (resultSignIn is Result.UserAuth) {
                        resultChannel.send(resultSignIn)
                        reduceState {
                            it.copy(showVerificationCodeInput = true, signInLoading = false, buttonEnabled = false)
                        }
                    }
                } catch (e: Exception) {
                    handleSignInError(e)
                }
            } else {
                reduceState {
                    it.copy(buttonEnabled = false)
                }
            }
        }
    }

    private fun onSubmitClickedCode() {
        viewModelScope.launch {
            val code = state.value.code
            if (codeValidation(code)) {
                reduceState {
                    it.copy(buttonEnabled = true)
                }
                try {
                    reduceState {
                        it.copy(showVerificationCodeInput = true, signInLoading = false)
                    }
                    val resultSession = sessionUseCase.execute(code)
                    if (resultSession is Result.ReceivedSession) {
                        resultChannel.send(resultSession)
                        reduceState {
                            it.copy(showVerificationCodeInput = false, signInLoading = true)
                        }
                    }
                    Log.d("qwerty", "send_code")
                } catch (e: Exception) {
                    handleSignInError(e)
                }
            }
            else {
                reduceState {
                    it.copy(buttonEnabled = false)
                }
            }
        }
    }

    private fun onUpdateEmail(email: String) = reduceState {
        it.copy(email = email, buttonEnabled = emailValidation(email))
    }

    private fun onUpdateVerificationCode(code: String) = reduceState {
        it.copy(code = code, buttonEnabled = codeValidation(code))
    }

    private fun handleSignInError(e: Exception) {
        println("Sign In Error: ${e.message}")
    }

    private fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun codeValidation(code: String): Boolean {
        val regex = "^\\d{6}\$".toRegex()
        return regex.matches(code)
    }
}
