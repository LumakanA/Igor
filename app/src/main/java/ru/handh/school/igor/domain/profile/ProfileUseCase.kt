package ru.handh.school.igor.domain.profile

import ru.handh.school.igor.data.IgorRepository
import ru.handh.school.igor.data.database.ProfileDao
import ru.handh.school.igor.data.database.ProfileEntity
import ru.handh.school.igor.domain.profile.getProfileResponse.ProfileData

class ProfileUseCase(
    private val igorRepository: IgorRepository,
    private val profileDao: ProfileDao
) {
    suspend fun execute(): ProfileData {
        val profileData = igorRepository.getProfile()
        val profile = profileData.data?.profile
        if (profile != null) {
            val profileEntity = ProfileEntity(
                name = profile.name ?: "",
                surname = profile.surname ?: "",
            )
            profileDao.insertProfile(profileEntity)
        }
        return profileData
    }
}