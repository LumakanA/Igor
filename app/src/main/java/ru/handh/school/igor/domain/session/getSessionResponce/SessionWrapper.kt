package ru.handh.school.igor.domain.session.getSessionResponce

import kotlinx.serialization.Serializable

@Serializable
data class SessionWrapper(
    val session: Session?
)