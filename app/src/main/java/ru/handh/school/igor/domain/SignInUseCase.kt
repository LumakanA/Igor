package ru.handh.school.igor.domain

import android.util.Log
import ru.handh.school.igor.data.IgorRepository
import java.util.UUID

class SignInUseCase {
    private val igorRepository = IgorRepository()
    suspend fun execute(signInRequest: SignInRequest) {
        igorRepository.postSignIn(getDeviceID(), signInRequest)
        Log.d("qwerty", "done")
    }
    private fun getDeviceID(): String {
        return UUID.randomUUID().toString()
    }
}