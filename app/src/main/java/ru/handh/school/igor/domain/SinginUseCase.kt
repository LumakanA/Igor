package ru.handh.school.igor.domain

import ru.handh.school.igor.data.IgorRepositoryDataSource

class SingInUseCase (private val igorRepository: IgorRepositoryDataSource) {
    suspend fun execute(email: String) {
        val request = SignInRequest(email)
    }
}