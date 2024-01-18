package ru.handh.school.igor.domain.projects.getProjectsResponce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectsWrapper(
    @SerialName("projects") val projects: List<Projects>?,
    @SerialName("hasMore") val hasMore: Boolean?
)