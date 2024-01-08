package ru.handh.school.igor.domain.projects

import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.domain.projects.getProjectsResponce.ProjectsData
import ru.handh.school.igor.domain.results.ResultProjects

class ProjectsUseCase(
    private val igorRepository: IgorRepository,
) {
    suspend fun execute(): ResultProjects<List<ProjectsData>> {
        return try {
            val projects = igorRepository.getProjects()
            ResultProjects.ReceivedProjects(data = projects)
        } catch (e: Exception) {
            ResultProjects.UnknownError()
        }
    }
}
