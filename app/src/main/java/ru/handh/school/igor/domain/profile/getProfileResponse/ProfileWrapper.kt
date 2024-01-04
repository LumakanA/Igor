package ru.handh.school.igor.domain.profile.getProfileResponse

import kotlinx.serialization.Serializable

@Serializable
data class ProfileWrapper(
    val profile: Profile
)