package ru.handh.school.igor.ui.screen.homepage

import ru.handh.school.igor.domain.projectDetails.getProjectDetailsResponce.ProjectDetails
import ru.handh.school.igor.domain.projects.getProjectsResponce.Projects

val InitialHomepageState = HomepageState(
    homepageLoading = false,
    homepageButtonLoading = false,
    error = false,
    errorMessage = null,
    projects = emptyList(),
    projectsDetails = null,
    selectedProjectId = null
)

data class HomepageState(
    val homepageLoading: Boolean,
    val homepageButtonLoading: Boolean,
    val error: Boolean,
    val errorMessage: String?,
    val projects: List<Projects>,
    val projectsDetails: ProjectDetails?,
    val selectedProjectId: String?
)
