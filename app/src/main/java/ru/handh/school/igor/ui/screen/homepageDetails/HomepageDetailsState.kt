package ru.handh.school.igor.ui.screen.homepageDetails

import ru.handh.school.igor.domain.projectDetails.getProjectDetailsResponce.ProjectDetails

val InitialHomepageDetailsState = HomepageDetailsState(
    homepageLoading = false,
    homepageButtonLoading = false,
    error = false,
    errorMessage = null,
    projectsDetails = null
)

data class HomepageDetailsState(
    val homepageLoading: Boolean,
    val homepageButtonLoading: Boolean,
    val error: Boolean,
    val errorMessage: String?,
    val projectsDetails: ProjectDetails?
)