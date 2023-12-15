package ru.handh.school.igor.domain.signin

import android.util.Log
import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.data.KeyValueStorage

class SignInUseCase(
    private val storage: KeyValueStorage,
    private val igorRepository: IgorRepository
) {
    suspend fun execute(signInRequest: SignInRequest) {
        igorRepository.postSignIn(storage.deviceId.toString(), signInRequest)
        Log.d("qwerty", "sign_done")
    }
}