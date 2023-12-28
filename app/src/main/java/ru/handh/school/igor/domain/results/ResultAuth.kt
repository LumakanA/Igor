package ru.handh.school.igor.domain.results

sealed class ResultAuth<T>(val data: T? = null) {
    class UserAuth<T>(data: T? = null) : ResultAuth<T>(data)
    class ReceivedSession<T>(data: T? = null) : ResultAuth<T>(data)
    class UnknownError<T> : ResultAuth<T>()
}