package ru.handh.school.igor.data

import io.ktor.client.statement.HttpResponse
import ru.handh.school.igor.domain.profile.getProfileResponse.ProfileData
import ru.handh.school.igor.domain.session.getSessionResponce.SessionData
import ru.handh.school.igor.domain.signin.SignInRequest

interface IgorRepositoryDataSource {
    suspend fun postSignIn(id: String, signInRequest: SignInRequest): HttpResponse
    suspend fun getSession(id: String, code: String): SessionData
    suspend fun postRefreshToken(refreshToken: String)
    suspend fun postSignOut()
    suspend fun getProfile(): ProfileData
    suspend fun getProjects()
    suspend fun getNotifications()
}