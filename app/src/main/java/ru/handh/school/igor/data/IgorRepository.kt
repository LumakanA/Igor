package ru.handh.school.igor.data

import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.statement.readText
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import ru.handh.school.igor.domain.SignInRequest

class IgorRepository : IgorRepositoryDataSource {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
                isLenient = true
                allowStructuredMapKeys = true
                prettyPrint = true
                coerceInputValues = true
                useArrayPolymorphism = true
                allowSpecialFloatingPointValues = true
                useAlternativeNames = true
            })
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun postSignIn(id: String, signInRequest: SignInRequest) {
        val url = "http://45.144.64.179/project/api/auth/signin"

        val requestBody = TextContent(
            text = kotlinx.serialization.json.Json.encodeToString(signInRequest),
            contentType = ContentType.Application.Json
        )

        try {
            client.post<Unit>(url) {
                body = requestBody
                headers {
                    append("X-Device-Id", id)
                }
            }
        } catch (e: ClientRequestException) {
            println("Request failed with status: ${e.response.status}")
            println("Response content: ${e.response.readText()}")
            throw e
        }
    }

    override suspend fun getSession() {
        TODO("Not yet implemented")
    }

    override suspend fun postRefreshToken() {
        TODO("Not yet implemented")
    }

    override suspend fun postSignOut() {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile() {
        TODO("Not yet implemented")
    }

    override suspend fun getProjects() {
        TODO("Not yet implemented")
    }

    override suspend fun getNotifications() {
        TODO("Not yet implemented")
    }
}
