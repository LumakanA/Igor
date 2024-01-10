package ru.handh.school.igor.data

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
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
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.handh.school.igor.domain.profile.getProfileResponse.ProfileData
import ru.handh.school.igor.domain.projects.getProjectsResponce.ProjectsData
import ru.handh.school.igor.domain.session.getSessionResponce.SessionData
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
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Ktor", message)
                }
            }
            level = LogLevel.ALL
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        storage.accessToken ?: "", storage.refreshToken ?: ""
                    )
                }
                refreshTokens {
                    val token = client.post(ApiUrls.REFRESH_TOKEN) {
                        markAsRefreshTokenRequest()
                        setBody(mapOf("refreshToken" to storage.refreshToken))
                    }.body<SessionData>()

                    storage.accessToken = token.data?.session?.accessToken
                    storage.refreshToken = token.data?.session?.refreshToken

                    BearerTokens(
                        accessToken = token.data?.session?.accessToken ?: "",
                        refreshToken = token.data?.session?.refreshToken ?: ""
                    )
                }
            }
        }
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
        code: String
    ): SessionData {
        return client.get(ApiUrls.GET_SESSION) {
            attributes.put(Auth.AuthCircuitBreaker, Unit)
            parameter("lifeTime", "5")
            header("X-Device-Id", id)
            header("X-OTP", code)
        }.body<SessionData>()
    }


    override suspend fun postRefreshToken() {
        TODO()
    }


    override suspend fun postSignOut() {
        try {
            client.post(ApiUrls.POST_SIGN_OUT) {
                attributes.put(Auth.AuthCircuitBreaker, Unit)
            }
            storage.accessToken = null
            storage.refreshToken = null
        } catch (e: ClientRequestException) {
            throw e
        }
    }

    override suspend fun getProfile(): ProfileData {
        try {
            return client.get(ApiUrls.GET_PROFILE) {
            }.body<ProfileData>()
        } catch (e: ClientRequestException) {
            throw e
        }
    }


    override suspend fun getProjects(): List<ProjectsData> {
        return client.get(ApiUrls.GET_PROJECTS) {
            parameter("limit", "50")
            parameter("offset", "100")
        }.body<List<ProjectsData>>()
    }

    override suspend fun getNotifications() {
        TODO("Not yet implemented")
    }
}