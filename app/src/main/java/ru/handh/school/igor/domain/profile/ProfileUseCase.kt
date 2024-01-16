package ru.handh.school.igor.domain.profile

import ru.handh.school.igor.data.IgorRepositoryImp
import ru.handh.school.igor.data.database.ProfileDao
import ru.handh.school.igor.data.database.ProfileEntity

class ProfileUseCase(
    private val igorRepositoryImp: IgorRepositoryImp,
    private val profileDao: ProfileDao
) {
    suspend fun execute(): List<ProfileEntity> {
        try {
            val profileData = igorRepositoryImp.getProfile()
            val profile = profileData.data?.profile
            if (profile != null) {
                val profileEntity = ProfileEntity(
                    name = profile.name.orEmpty(),
                    surname = profile.surname.orEmpty(),
                    icon = profile.icon.orEmpty()
                )
                profileDao.replaceProfile(profileEntity)
                return listOf(profileEntity)
            }
        } catch (e: Exception) {
        }
        return emptyList()
    }
}

