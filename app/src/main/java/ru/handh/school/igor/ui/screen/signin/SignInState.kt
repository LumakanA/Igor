package ru.handh.school.igor.ui.screen.signin

val InitialSignInState = SignInState(
    signInLoading = false,
    username = ""
)

data class SignInState(
    val signInLoading: Boolean,
    val username: String
)
