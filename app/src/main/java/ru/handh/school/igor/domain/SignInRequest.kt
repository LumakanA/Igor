package ru.handh.school.igor.domain

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(val email: String)
