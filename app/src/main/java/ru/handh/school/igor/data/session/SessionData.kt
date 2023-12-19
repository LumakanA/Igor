package ru.handh.school.igor.data.session

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionData(
    @SerialName("data") val data: SessionWrapper
)