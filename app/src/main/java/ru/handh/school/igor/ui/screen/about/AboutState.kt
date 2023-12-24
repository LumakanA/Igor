package ru.handh.school.igor.ui.screen.about

val InitialAboutState = AboutState(
    accessToken = null,
    aboutLoading = false
)

data class AboutState(
    val accessToken: String?,
    val aboutLoading: Boolean
)