package ru.handh.school.igor.domain.signin

import ru.handh.school.igor.data.IgorRepositoryImp
import ru.handh.school.igor.domain.results.ResultAuth

class SignInUseCase(
    private val igorRepositoryImp: IgorRepositoryImp
) {
    suspend fun execute(email: String): ResultAuth<Unit> {
        return try {
            igorRepositoryImp.postSignIn(SignInRequest(email))
            ResultAuth.UserAuth()
        } catch (e: Exception) {
            ResultAuth.UnknownError()
        }
    }
}