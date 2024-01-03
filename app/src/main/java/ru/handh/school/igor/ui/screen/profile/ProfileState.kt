package ru.handh.school.igor.ui.screen.profile

val InitialProfileState = ProfileState(
    profileLoading = false
)

data class ProfileState(
    val profileLoading: Boolean
)