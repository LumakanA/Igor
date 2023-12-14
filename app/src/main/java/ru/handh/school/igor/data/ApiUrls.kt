package ru.handh.school.igor.data

class ApiUrls {
    companion object {
        private const val BASE_URL = "http://45.144.64.179/project/api"
        const val POST_SIGN_IN = "$BASE_URL/auth/signin"
        const val GET_SESSION = "$BASE_URL/auth/session"
        const val REFRESH_TOKEN = "$BASE_URL/auth/refresh"
        const val POST_SIGN_OUT = "$BASE_URL/auth/signout"
        const val GET_PROFILE = "$BASE_URL/account/profile"
        const val GET_PROJECTS = "$BASE_URL/projects"
        const val GET_NOTIFICATIONS = "$BASE_URL/notifications"
    }
}