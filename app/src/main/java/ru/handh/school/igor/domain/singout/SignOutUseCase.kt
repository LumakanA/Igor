package ru.handh.school.igor.domain.singout

import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.domain.Result

class SignOutUseCase(
    private val igorRepository: IgorRepository
) {
    suspend fun execute(): Result<Unit> {
        return try {
            igorRepository.postSignOut()
            Result.ReceivedSession()
        } catch (e: Exception) {
            Result.UnknownError()
        }
    }
}