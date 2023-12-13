package ru.handh.school.igor.ui.screen.signin

val InitialSignInState = SignInState(
    signInLoading = false,
    email = ""
)

data class SignInState(
    val signInLoading: Boolean,
    val email: String
)
