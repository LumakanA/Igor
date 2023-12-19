package ru.handh.school.igor.ui.screen.signin

val InitialSignInState = SignInState(
    signInLoading = false,
    email = "",
    code = "",
    showVerificationCodeInput = false,
    buttonEnabled = false
)

data class SignInState(
    val signInLoading: Boolean,
    val email: String,
    val code: String,
    val showVerificationCodeInput: Boolean,
    val buttonEnabled: Boolean
)
