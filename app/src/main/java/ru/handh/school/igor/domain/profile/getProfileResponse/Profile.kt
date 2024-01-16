package ru.handh.school.igor.domain.profile.getProfileResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("name") val name: String?,
    @SerialName("surname") val surname: String?,
    @SerialName("icon") val icon: String? = null
)
