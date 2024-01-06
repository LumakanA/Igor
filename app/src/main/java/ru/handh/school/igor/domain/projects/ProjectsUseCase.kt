package ru.handh.school.igor.domain.projects

import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.domain.projects.getProjectsResponce.ProjectsData

class ProjectsUseCase(
    private val igorRepository: IgorRepository,
) {
    suspend fun execute(): List<ProjectsData> {
        return igorRepository.getProjects()
    }
}

