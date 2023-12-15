package ru.handh.school.igor.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.handh.school.igor.domain.session.SessionResponse
import ru.handh.school.igor.domain.signin.SignInRequest

class IgorRepository : IgorRepositoryDataSource {
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
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

    override suspend fun postSignIn(id: String, signInRequest: SignInRequest) {
        val requestBody = TextContent(
            text = Json.encodeToString(signInRequest),
            contentType = ContentType.Application.Json
        )

        try {
            client.post(ApiUrls.POST_SIGN_IN) {
                setBody(requestBody)
                headers {
                    append("X-Device-Id", id)
                }
            }
        } catch (e: ClientRequestException) {
            println("Request failed with status: ${e.response.status}")
            println("Response content: ${e.response.bodyAsText()}")
            throw e
        }
    }

    override suspend fun getSession(id: String, sessionResponse: SessionResponse) {
        try {
            val response = client.get(ApiUrls.GET_SESSION) {
                val requestBody = TextContent(
                    text = Json.encodeToString(sessionResponse),
                    contentType = ContentType.Application.Json
                )
                headers {
                    setBody(requestBody)
                    append("X-Device-Id", id)
                    append("X-OPT", sessionResponse.code)
                }
            }

            if (response.status.isSuccess()) {
                TODO()
            } else {
                println("Request failed with status: ${response.status}")
                println("Response content: ${response.bodyAsText()}")
            }
        } catch (e: ClientRequestException) {
            println("Request failed with status: ${e.response.status}")
            println("Response content: ${e.response.bodyAsText()}")
            throw e
        }
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
