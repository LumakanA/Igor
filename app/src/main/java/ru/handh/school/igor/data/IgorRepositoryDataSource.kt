package ru.handh.school.igor.data

import io.ktor.client.statement.HttpResponse
import ru.handh.school.igor.data.session.Session
import ru.handh.school.igor.domain.results.ResultAuth
import ru.handh.school.igor.domain.session.SessionResponse
import ru.handh.school.igor.domain.signin.SignInRequest

interface IgorRepositoryDataSource {
    suspend fun postSignIn(id: String, signInRequest: SignInRequest): HttpResponse
    suspend fun getSession(id: String, sessionResponse: SessionResponse): ResultAuth<Session>
    suspend fun postRefreshToken(refreshToken: String)
    suspend fun postSignOut()
    suspend fun getProfile()
    suspend fun getProjects()
    suspend fun getNotifications()
}