package ru.handh.school.igor.ui.screen.about

val InitialAboutState = AboutState(
    aboutLoading = false
)

data class AboutState(
    val aboutLoading: Boolean
)