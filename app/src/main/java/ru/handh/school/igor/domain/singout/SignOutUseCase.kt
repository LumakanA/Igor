package ru.handh.school.igor.domain.singout

import ru.handh.school.igor.data.IgorRepositoryImp
import ru.handh.school.igor.domain.results.ResultProfile

class SignOutUseCase(
    private val igorRepositoryImp: IgorRepositoryImp
) {
    suspend fun execute(): ResultProfile<Unit> {
        return try {
            igorRepositoryImp.postSignOut()
            ResultProfile.LoggedOut()
        } catch (e: Exception) {
            ResultProfile.UnknownError()
        }
    }
}
