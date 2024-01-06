package ru.handh.school.igor.domain.projects.getProjectsResponce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectsData(
    @SerialName("data") val data: ProjectsWrapper?
)