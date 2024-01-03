package ru.handh.school.igor.domain.session.getSessionResponce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    @SerialName("accessToken") val accessToken: String?,
    @SerialName("refreshToken") val refreshToken: String?
)