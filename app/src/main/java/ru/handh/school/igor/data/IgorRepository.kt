package ru.handh.school.igor.data

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.handh.school.igor.data.session.Session
import ru.handh.school.igor.data.session.SessionData
import ru.handh.school.igor.domain.results.ResultAuth
import ru.handh.school.igor.domain.session.SessionResponse
import ru.handh.school.igor.domain.signin.SignInRequest

class IgorRepository(
    private val storage: KeyValueStorage
) : IgorRepositoryDataSource {
    private val client = HttpClient(CIO) {
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
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Ktor", message)
                }
            }
            level = LogLevel.ALL
        }
//        install(Auth) {
//            bearer {
//                refreshTokens {
//                    val tokens = client.post {
//                        url(ApiUrls.REFRESH_TOKEN)
//                        parameter("refreshToken", storage.refreshToken)
//                        markAsRefreshTokenRequest()
//                    }.body<SessionData>()
//
//                    BearerTokens(
//                        accessToken = tokens.data.session.accessToken,
//                        refreshToken = tokens.data.session.refreshToken
//                    )
//                }
//            }
//        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    override suspend fun postSignIn(id: String, signInRequest: SignInRequest): HttpResponse {
        return try {
            val response = client.post(ApiUrls.POST_SIGN_IN) {
                setBody(signInRequest)
                headers {
                    append("X-Device-Id", id)
                }
            }
            response
        } catch (e: ClientRequestException) {
            throw e
        }
    }

    override suspend fun getSession(
        id: String,
        sessionResponse: SessionResponse
    ): ResultAuth<Session> {
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
                ResultAuth.ReceivedSession(session)
            } else {
                ResultAuth.UnknownError()
            }
        } catch (e: ClientRequestException) {
            ResultAuth.UnknownError()
        }
    }


    override suspend fun postRefreshToken(refreshToken: String) {
    }


    override suspend fun postSignOut() {
        try {
            val accessToken = storage.accessToken

            if (accessToken != null) {
                client.post(ApiUrls.POST_SIGN_OUT) {
                    header("Authorization", "Bearer $accessToken")
                }
                storage.accessToken = null
                storage.refreshToken = null
            }
        } catch (e: ClientRequestException) {
            throw e
        }
    }

    override suspend fun getProfile() {
        return client.get(ApiUrls.GET_PROFILE) {
            headers {
                append("Authorization", storage.accessToken ?: "")
            }
        }.body()
    }

    override suspend fun getProjects() {
        TODO("Not yet implemented")
    }

    override suspend fun getNotifications() {
        TODO("Not yet implemented")
    }
}