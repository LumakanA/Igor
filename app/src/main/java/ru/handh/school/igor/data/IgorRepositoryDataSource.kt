package ru.handh.school.igor.data

import ru.handh.school.igor.domain.session.SessionResponse
import ru.handh.school.igor.domain.signin.SignInRequest

interface IgorRepositoryDataSource {
    suspend fun postSignIn(id: String, signInRequest: SignInRequest)
    suspend fun getSession(id: String, sessionResponse: SessionResponse)
    suspend fun postRefreshToken()
    suspend fun postSignOut()
    suspend fun getProfile()
    suspend fun getProjects()
    suspend fun getNotifications()
}