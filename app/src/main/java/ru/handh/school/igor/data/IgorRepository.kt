package ru.handh.school.igor.data

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
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

    override suspend fun postSignIn(signInRequest: SignInRequest) {
        TODO("Not yet implemented")
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
