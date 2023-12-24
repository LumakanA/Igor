package ru.handh.school.igor.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.handh.school.igor.data.session.Session
import ru.handh.school.igor.data.session.SessionData
import ru.handh.school.igor.domain.Result
import ru.handh.school.igor.domain.session.SessionResponse
import ru.handh.school.igor.domain.signin.SignInRequest

class IgorRepository(
    private val storage: KeyValueStorage
) : IgorRepositoryDataSource {
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

    override suspend fun postSignIn(id: String, signInRequest: SignInRequest): HttpResponse {
        val requestBody = TextContent(
            text = Json.encodeToString(signInRequest),
            contentType = ContentType.Application.Json
        )

        return try {
            val response = client.post(ApiUrls.POST_SIGN_IN) {
                setBody(requestBody)
                headers {
                    append("X-Device-Id", id)
                }
            }
            response
        } catch (e: ClientRequestException) {
            throw e
        }
    }

    override suspend fun getSession(id: String, sessionResponse: SessionResponse): Result<Session> {
        return try {
            val response = client.get(ApiUrls.GET_SESSION) {
                parameter("lifeTime", "5")
                header("X-Device-Id", id)
                header("X-OTP", sessionResponse.code)
            }
            if (response.status.isSuccess()) {
                val sessionData = Json.decodeFromString<SessionData>(response.bodyAsText())
                val session = sessionData.data.session
                storage.accessToken = session.accessToken
                storage.refreshToken = session.refreshToken
                Result.ReceivedSession(session)
            } else {
                Result.UnknownError()
            }
        } catch (e: ClientRequestException) {
            Result.UnknownError()
        }
    }


    override suspend fun postRefreshToken(): Result<Session> {
        return try {
            val refreshToken = storage.refreshToken

            if (refreshToken != null) {
                val response = client.post(ApiUrls.REFRESH_TOKEN) {
                    header("Authorization", "Bearer $refreshToken")
                    setBody(
                        TextContent(
                            Json.encodeToString { "refreshToken" to refreshToken },
                            ContentType.Application.Json
                        )
                    )
                }
                if (response.status.isSuccess()) {
                    val sessionData = Json.decodeFromString<SessionData>(response.bodyAsText())
                    val session = sessionData.data.session

                    storage.accessToken = session.accessToken
                    storage.refreshToken = session.refreshToken

                    Result.ReceivedSession(session)
                } else {
                    Result.UnknownError()
                }
            } else {
                Result.UnknownError()
            }
        } catch (e: ClientRequestException) {
            Result.UnknownError()
        }
    }


    override suspend fun postSignOut() {
        try {
            val refreshToken = storage.refreshToken

            if (refreshToken != null) {
                client.post(ApiUrls.POST_SIGN_OUT) {
                    header("Authorization", "Bearer $refreshToken")
                }
                storage.accessToken = null
                storage.refreshToken = null
            }
        } catch (e: ClientRequestException) {
            throw e
        }
    }

    override suspend fun getProfile() {
        try {
            client.get(ApiUrls.GET_PROFILE) {
                header("Authorization", "Bearer")
            }
        } catch (e: ClientRequestException) {
            throw e
        }
    }

    override suspend fun getProjects() {
        TODO("Not yet implemented")
    }

    override suspend fun getNotifications() {
        TODO("Not yet implemented")
    }
}
