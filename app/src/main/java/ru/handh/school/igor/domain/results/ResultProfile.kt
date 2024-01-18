package ru.handh.school.igor.domain.results

sealed class ResultProfile<T>(val data: T? = null) {
    class LoggedOut<T> : ResultProfile<T>()
    class UnknownError<T> : ResultProfile<T>()
}