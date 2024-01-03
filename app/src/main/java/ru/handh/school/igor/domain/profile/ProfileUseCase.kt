package ru.handh.school.igor.domain.profile

import ru.handh.school.igor.data.IgorRepository

class ProfileUseCase(
    private val igorRepository: IgorRepository,
) {
    suspend fun execute() {
        igorRepository.getProfile()
    }
}