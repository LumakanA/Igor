package ru.handh.school.igor.domain.profile

import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.domain.results.ResultAuth

class ProfileUseCase(
    private val igorRepository: IgorRepository,
) {
    suspend fun execute(): ResultAuth<Unit> {
        return try {
            igorRepository.getProfile()
            ResultAuth.ReceivedSession()
        } catch (e: Exception) {
            ResultAuth.UnknownError()
        }
    }
}