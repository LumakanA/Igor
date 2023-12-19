package ru.handh.school.igor.domain.session

import android.util.Log
import ru.handh.school.igor.data.DeviceIdProvider
import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.data.KeyValueStorage
import ru.handh.school.igor.domain.Result

class SessionUseCase(
    private val igorRepository: IgorRepository,
    private val deviceIdProvider: DeviceIdProvider,
    private val keyValueStorage: KeyValueStorage
) {
    suspend fun execute(code: String): Result<Unit> {
        return try {
            val result = igorRepository.getSession(deviceIdProvider.deviceId, SessionResponse(code))

            if (result is Result.ReceivedSession) {
                keyValueStorage.accessToken = result.data.toString()
                Log.d("SessionUseCase", "Токен доступа сохранен: ${result.data}")
                Result.ReceivedSession()
            } else {
                Log.d("SessionUseCase", "UnknownError result received")
                Result.UnknownError()
            }
        } catch (e: Exception) {
            Log.e("SessionUseCase", "Exception occurred: ${e.message}")
            Result.UnknownError()
        }
    }
}



