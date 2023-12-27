package ru.handh.school.igor.ui.screen.signin

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
) : BaseViewModel<SignInState, SignInViewAction>(InitialSignInState) {
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
                    it.copy(buttonEnabled = true, signInLoading = true)
                }
                try {
                    val resultSignIn = signInUseCase.execute(email)
                    resultChannel.send(resultSignIn)
                    when (resultSignIn) {
                        is Result.UserAuth -> {
                            reduceState {
                                it.copy(
                                    showVerificationCodeInput = true,
                                    signInLoading = false,
                                    buttonEnabled = false
                                )
                            }
                        }
                        is Result.UnknownError -> {
                            reduceState {
                                it.copy(signInLoading = false)
                            }
                        }

                        is Result.ReceivedSession -> {
                            resultChannel.send(resultSignIn)
                        }
                    }
                } catch (e: Exception) {
                    reduceState {
                        it.copy(signInLoading = false)
                    }
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
                    it.copy(buttonEnabled = true, signInLoading = true)
                }
                try {
                    val resultSession = sessionUseCase.execute(code)
                    resultChannel.send(resultSession)
                    when (resultSession) {
                        is Result.ReceivedSession -> {
                            reduceState {
                                it.copy(
                                    showVerificationCodeInput = false,
                                    signInLoading = false,
                                    buttonEnabled = false
                                )
                            }
                        }
                        is Result.UnknownError -> {
                            reduceState {
                                it.copy(signInLoading = false)
                            }
                        }

                        is Result.UserAuth -> {
                            resultChannel.send(resultSession)
                        }
                    }
                } catch (e: Exception) {
                    reduceState {
                        it.copy(signInLoading = false)
                    }
                }
            } else {
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

    private fun emailValidation(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun codeValidation(code: String): Boolean {
        val regex = "^\\d{6}\$".toRegex()
        return regex.matches(code)
    }
}
