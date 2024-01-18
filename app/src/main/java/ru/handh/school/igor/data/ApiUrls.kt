package ru.handh.school.igor.data

class ApiUrls {
    companion object {
        const val BASE_URL = "http://45.144.64.179/project/api/"
        const val POST_SIGN_IN = "auth/signin"
        const val GET_SESSION = "auth/session"
        const val REFRESH_TOKEN = "auth/refresh"
        const val POST_SIGN_OUT = "auth/signout"
        const val GET_PROFILE = "account/profile"
        const val GET_PROJECTS = "projects"
    }
}