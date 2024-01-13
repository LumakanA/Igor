package ru.handh.school.igor.domain.session

import ru.handh.school.igor.data.IgorRepositoryImp
import ru.handh.school.igor.data.KeyValueStorage
import ru.handh.school.igor.domain.results.ResultAuth

class SessionUseCase(
    private val igorRepositoryImp: IgorRepositoryImp,
    private val keyValueStorage: KeyValueStorage
) {
    suspend fun execute(code: String): ResultAuth<Unit> {
        return try {
            val result = igorRepositoryImp.getSession(code)

            keyValueStorage.accessToken = result.data?.session?.accessToken
            keyValueStorage.refreshToken = result.data?.session?.refreshToken
            ResultAuth.ReceivedSession()
        } catch (e: Exception) {
            ResultAuth.UnknownError()
        }
    }
}



