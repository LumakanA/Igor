package ru.handh.school.igor.domain

sealed class Result<T>(val data: T? = null) {
    class UserAuth<T>(data: T? = null) : Result<T>(data)
    class ReceivedSession<T>(data: T? = null) : Result<T>(data)
    class UnknownError<T> : Result<T>()
}