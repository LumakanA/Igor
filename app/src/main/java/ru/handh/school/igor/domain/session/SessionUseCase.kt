package ru.handh.school.igor.domain.session

import android.util.Log
import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.data.KeyValueStorage

class SessionUseCase(
    private val storage: KeyValueStorage,
    private val igorRepository: IgorRepository
) {
    suspend fun execute(sessionResponse: SessionResponse) {
        Log.d("qwerty", "session_done")
        igorRepository.getSession(storage.deviceId.toString(), sessionResponse)
    }
}