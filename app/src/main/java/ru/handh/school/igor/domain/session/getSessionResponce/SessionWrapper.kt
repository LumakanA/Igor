package ru.handh.school.igor.domain.session.getSessionResponce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionWrapper(
    @SerialName("session") val session: Session?
)