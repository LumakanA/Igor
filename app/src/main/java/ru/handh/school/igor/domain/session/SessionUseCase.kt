package ru.handh.school.igor.domain.session

import android.util.Log
import ru.handh.school.igor.data.DeviceIdProvider
import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.data.KeyValueStorage
import ru.handh.school.igor.domain.results.ResultAuth

class SessionUseCase(
    private val igorRepository: IgorRepository,
    private val deviceIdProvider: DeviceIdProvider,
    private val keyValueStorage: KeyValueStorage
) {
    suspend fun execute(code: String): ResultAuth<Unit> {
        return try {
            val result = igorRepository.getSession(deviceIdProvider.deviceId, code)

            keyValueStorage.accessToken = result.data?.session?.accessToken
            keyValueStorage.refreshToken = result.data?.session?.refreshToken
//            Log.d("SessionUseCase", "Токен доступа сохранен: ${result.data}")
            Log.d("SessionUseCase", "UnknownError result received")
            ResultAuth.ReceivedSession()
        } catch (e: Exception) {
            Log.e("SessionUseCase", "Exception occurred: ${e.message}")
            ResultAuth.UnknownError()
        }
    }
}



