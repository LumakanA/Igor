package ru.handh.school.igor.ui.screen.homepage

import ru.handh.school.igor.domain.projects.getProjectsResponce.Projects

val InitialHomepageState = HomepageState(
    homepageLoading = false,
    homepageButtonLoading = false,
    error = false,
    errorMessage = null,
    projects = emptyList()
)

data class HomepageState(
    val homepageLoading: Boolean,
    val homepageButtonLoading: Boolean,
    val error: Boolean,
    val errorMessage: String?,
    val projects: List<Projects>
)