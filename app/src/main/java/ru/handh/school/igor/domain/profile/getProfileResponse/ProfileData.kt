package ru.handh.school.igor.domain.profile.getProfileResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    @SerialName("data") val data: ProfileWrapper?
)