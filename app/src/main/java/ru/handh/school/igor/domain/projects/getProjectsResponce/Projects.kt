package ru.handh.school.igor.domain.projects.getProjectsResponce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Projects(
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?
)