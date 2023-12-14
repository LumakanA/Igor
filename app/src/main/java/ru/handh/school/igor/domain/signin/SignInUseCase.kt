package ru.handh.school.igor.domain.signin

import android.util.Log
import ru.handh.school.igor.data.IgorRepository
import java.util.UUID

class SignInUseCase {
    private val igorRepository = IgorRepository()
    suspend fun execute(signInRequest: SignInRequest) {
        igorRepository.postSignIn(getDeviceID(), signInRequest)
        Log.d("qwerty", "sign_done")
    }

    private fun getDeviceID(): String {
        return UUID.randomUUID().toString()
    }
}