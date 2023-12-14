package ru.handh.school.igor.ui.screen.signin

sealed interface SignInViewAction {
    data object SubmitClicked : SignInViewAction
    data object SubmitClickedCode : SignInViewAction
    data class UpdateEmail(val email: String) : SignInViewAction
    data class UpdateVerificationCode(val code: String) : SignInViewAction
}