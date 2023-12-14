package ru.handh.school.igor.domain.session

import android.util.Log
import ru.handh.school.igor.data.IgorRepository
import java.util.UUID

class SessionUseCase {
    private val igorRepository = IgorRepository()

    suspend fun execute(sessionResponse: SessionResponse) {
        Log.d("qwerty", "session_done")
        igorRepository.getSession(getDeviceID(), sessionResponse, lifetime = 5)
    }

    private fun getDeviceID(): String {
        return UUID.randomUUID().toString()
    }
}