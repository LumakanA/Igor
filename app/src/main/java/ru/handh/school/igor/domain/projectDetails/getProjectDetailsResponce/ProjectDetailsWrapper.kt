package ru.handh.school.igor.domain.projectDetails.getProjectDetailsResponce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectDetailsWrapper(
    val projectDetails: List<ProjectDetails>?,
    @SerialName("hasMore") val hasMore: Boolean?
)