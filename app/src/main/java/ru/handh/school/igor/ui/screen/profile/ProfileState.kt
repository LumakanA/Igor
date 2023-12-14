package ru.handh.school.igor.ui.screen.profile

val InitialProfileState = ProfileState(
    ProfileLoading = false
)

data class ProfileState(
    val ProfileLoading: Boolean
)