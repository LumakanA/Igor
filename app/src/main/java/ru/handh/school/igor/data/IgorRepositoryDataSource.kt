package ru.handh.school.igor.data

import ru.handh.school.igor.domain.SignInRequest

interface IgorRepositoryDataSource {
    suspend fun postSignIn(signInRequest: SignInRequest)
    suspend fun getSession()
    suspend fun postRefreshToken()
    suspend fun postSignOut()
    suspend fun getProfile()
    suspend fun getProjects()
    suspend fun getNotifications()
}