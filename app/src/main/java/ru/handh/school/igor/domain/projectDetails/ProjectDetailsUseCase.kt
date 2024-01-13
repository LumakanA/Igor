package ru.handh.school.igor.domain.projectDetails

import ru.handh.school.igor.data.IgorRepositoryImp
import ru.handh.school.igor.domain.projectDetails.getProjectDetailsResponce.ProjectDetailsData
import ru.handh.school.igor.domain.results.ResultProjects

class ProjectDetailsUseCase(
    private val igorRepositoryImp: IgorRepositoryImp,
) {
    suspend fun execute(id: String): ResultProjects<ProjectDetailsData> {
        return try {
            val projects = igorRepositoryImp.getProjectsDetails(id)
            ResultProjects.ReceivedProjectsDetails(data = projects)
        } catch (e: Exception) {
            ResultProjects.UnknownError()
        }
    }
}