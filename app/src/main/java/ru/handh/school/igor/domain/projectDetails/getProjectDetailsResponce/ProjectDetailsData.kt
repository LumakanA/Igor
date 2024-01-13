package ru.handh.school.igor.domain.projectDetails.getProjectDetailsResponce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectDetailsData(
    @SerialName("data") val data: ProjectDetails?
)