package ru.handh.school.igor.domain.profile.getProfileResponse

import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    val data: ProfileWrapper?
)