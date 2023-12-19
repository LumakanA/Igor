package ru.handh.school.igor.domain.signin

import android.util.Log
import ru.handh.school.igor.data.DeviceIdProvider
import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.domain.Result

class SignInUseCase(
    private val igorRepository: IgorRepository,
    private val deviceIdProvider: DeviceIdProvider
) {
    suspend fun execute(email: String): Result<Unit> {
        return try {
            igorRepository.postSignIn(deviceIdProvider.deviceId, SignInRequest(email))
            Log.d("qwerty", "sign_done")
            Result.UserAuth()
        } catch (e: Exception) {
            Result.UnknownError()
        }
    }
}