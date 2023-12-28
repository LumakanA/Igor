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
            val result = igorRepository.getSession(deviceIdProvider.deviceId, SessionResponse(code))

            if (result is ResultAuth.ReceivedSession) {
                keyValueStorage.accessToken = result.data.toString()
                Log.d("SessionUseCase", "Токен доступа сохранен: ${result.data}")
                ResultAuth.ReceivedSession()
            } else {
                Log.d("SessionUseCase", "UnknownError result received")
                ResultAuth.UnknownError()
            }
        } catch (e: Exception) {
            Log.e("SessionUseCase", "Exception occurred: ${e.message}")
            ResultAuth.UnknownError()
        }
    }
}



