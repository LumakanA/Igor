package ru.handh.school.igor.domain.singout

import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.domain.results.ResultProfile

class SignOutUseCase(
    private val igorRepository: IgorRepository
) {
    suspend fun execute(): ResultProfile<Unit> {
        return try {
            igorRepository.postSignOut()
            ResultProfile.LoggedOut()
        } catch (e: Exception) {
            ResultProfile.UnknownError()
        }
    }
}
