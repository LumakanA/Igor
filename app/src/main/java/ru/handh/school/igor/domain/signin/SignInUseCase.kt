package ru.handh.school.igor.domain.signin

import android.util.Log
import ru.handh.school.igor.data.DeviceIdProvider
import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.domain.results.ResultAuth

class SignInUseCase(
    private val igorRepository: IgorRepository,
    private val deviceIdProvider: DeviceIdProvider
) {
    suspend fun execute(email: String): ResultAuth<Unit> {
        return try {
            igorRepository.postSignIn(deviceIdProvider.deviceId, SignInRequest(email))
            Log.d("qwerty", "sign_done")
            ResultAuth.UserAuth()
        } catch (e: Exception) {
            ResultAuth.UnknownError()
        }
    }
}