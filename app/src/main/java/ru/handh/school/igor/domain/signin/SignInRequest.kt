package ru.handh.school.igor.domain.signin

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(val email: String)
