package ru.handh.school.igor.ui.screen.profile

val InitialProfileState = ProfileState(
    accessToken = null,
    profileLoading = false
)

data class ProfileState(
    val accessToken: String?,
    val profileLoading: Boolean
)