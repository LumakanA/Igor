package ru.handh.school.igor.domain.projectDetails.getProjectDetailsResponce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProjectDetails(
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?
)