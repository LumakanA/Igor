package ru.handh.school.igor.domain.singout

import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.domain.results.ResultAuth

class SignOutUseCase(
    private val igorRepository: IgorRepository
) {
    suspend fun execute(): ResultAuth<Unit> {
        return try {
            igorRepository.postSignOut()
            ResultAuth.ReceivedSession()
        } catch (e: Exception) {
            ResultAuth.UnknownError()
        }
    }
}
