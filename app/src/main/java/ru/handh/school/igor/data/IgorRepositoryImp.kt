package ru.handh.school.igor.data

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.handh.school.igor.domain.profile.getProfileResponse.ProfileData
import ru.handh.school.igor.domain.projectDetails.getProjectDetailsResponce.ProjectDetailsData
import ru.handh.school.igor.domain.projects.getProjectsResponce.ProjectsData
import ru.handh.school.igor.domain.session.getSessionResponce.SessionData
import ru.handh.school.igor.domain.signin.SignInRequest

private const val lifeTime = 5
private const val limit = 50
private const val offset = 100

class IgorRepositoryImp(
    private val storage: KeyValueStorage
) : IgorRepository {
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
                    val provider = client.plugin(Auth)
                        .providers
                        .filterIsInstance<BearerAuthProvider>()
                        .firstOrNull()
                    provider?.clearToken()
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
            url(ApiUrls.BASE_URL)
        }
    }

    override suspend fun postSignIn(signInRequest: SignInRequest): HttpResponse {
        return try {
            client.post(ApiUrls.POST_SIGN_IN) {
                header("X-Device-Id", storage.deviceId)
                setBody(signInRequest)
            }
        } catch (e: ClientRequestException) {
            throw e
        }
    }

    override suspend fun getSession(
        code: String
    ): SessionData {
        return client.get(ApiUrls.GET_SESSION) {
            parameter("lifeTime", lifeTime)
            header("X-Device-Id", storage.deviceId)
            header("X-OTP", code)
        }.body<SessionData>()
    }

    override suspend fun postSignOut() {
        try {
            client.post(ApiUrls.POST_SIGN_OUT) {
                val provider = client.plugin(Auth)
                    .providers
                    .filterIsInstance<BearerAuthProvider>()
                    .firstOrNull()
                provider?.clearToken()
                storage.accessToken = null
                storage.refreshToken = null
            }
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

    override suspend fun getProjects(): ProjectsData {
        return client.get(ApiUrls.GET_PROJECTS) {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body<ProjectsData>()
    }

    override suspend fun getProjectsDetails(id: String): ProjectDetailsData {
        return client.get(ApiUrls.GET_PROJECTS + "/${id}") {
        }.body<ProjectDetailsData>()
    }
}