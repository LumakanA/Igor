package ru.handh.school.igor.ui.screen.homepage

import ru.handh.school.igor.domain.projects.getProjectsResponce.ProjectsData

val InitialHomepageState = HomepageState(
    aboutLoading = false,
    projects = emptyList()
)

data class HomepageState(
    val aboutLoading: Boolean,
    val projects: List<ProjectsData>
)