package ru.handh.school.igor.ui.screen.signin

sealed interface SignInViewAction {
    data object SubmitClicked : SignInViewAction
    data class UpdateEmail(val email: String) : SignInViewAction
}