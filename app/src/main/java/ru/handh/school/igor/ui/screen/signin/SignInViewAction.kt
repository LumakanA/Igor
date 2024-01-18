package ru.handh.school.igor.ui.screen.signin

sealed interface SignInViewAction {
    data object SendEmail : SignInViewAction
    data object SendCode : SignInViewAction
    data class UpdateEmail(val email: String) : SignInViewAction
    data class UpdateVerificationCode(val code: String) : SignInViewAction
}