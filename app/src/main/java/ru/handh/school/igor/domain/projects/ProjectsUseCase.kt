package ru.handh.school.igor.domain.projects

import ru.handh.school.igor.data.IgorRepositoryImp
import ru.handh.school.igor.domain.projects.getProjectsResponce.ProjectsData
import ru.handh.school.igor.domain.results.ResultProjects

class ProjectsUseCase(
    private val igorRepositoryImp: IgorRepositoryImp,
) {
    suspend fun execute(): ResultProjects<ProjectsData> {
        return try {
            val projects = igorRepositoryImp.getProjects()
            ResultProjects.ReceivedProjects(data = projects)
        } catch (e: Exception) {
            ResultProjects.UnknownError()
        }
    }
}
