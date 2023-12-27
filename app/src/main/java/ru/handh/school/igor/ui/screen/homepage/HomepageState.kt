package ru.handh.school.igor.ui.screen.homepage

val InitialHomepageState = HomepageState(
    accessToken = null,
    aboutLoading = false
)

data class HomepageState(
    val accessToken: String?,
    val aboutLoading: Boolean
)