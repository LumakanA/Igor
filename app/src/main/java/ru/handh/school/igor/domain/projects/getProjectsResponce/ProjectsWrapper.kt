package ru.handh.school.igor.domain.projects.getProjectsResponce

import kotlinx.serialization.Serializable

@Serializable
data class ProjectsWrapper(
    val projects: List<Projects>?
)