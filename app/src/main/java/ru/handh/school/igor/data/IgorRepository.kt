package ru.handh.school.igor.data

import io.ktor.client.statement.HttpResponse
import ru.handh.school.igor.domain.profile.getProfileResponse.ProfileData
import ru.handh.school.igor.domain.projectDetails.getProjectDetailsResponce.ProjectDetailsData
import ru.handh.school.igor.domain.projects.getProjectsResponce.ProjectsData
import ru.handh.school.igor.domain.session.getSessionResponce.SessionData
import ru.handh.school.igor.domain.signin.SignInRequest

interface IgorRepository {
    suspend fun postSignIn(signInRequest: SignInRequest): HttpResponse
    suspend fun getSession(code: String): SessionData
    suspend fun postSignOut()
    suspend fun getProfile(): ProfileData
    suspend fun getProjects(): ProjectsData
    suspend fun getProjectsDetails(id: String): ProjectDetailsData
}