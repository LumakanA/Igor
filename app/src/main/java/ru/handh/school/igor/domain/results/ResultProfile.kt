package ru.handh.school.igor.domain.results

sealed class ResultProfile<T>(val data: T? = null) {
    class ReceivedProfile<T>/*TODO()*/ : ResultProfile<T>()
    class LoggedOut<T> : ResultProfile<T>()
    class UnknownError<T> : ResultProfile<T>()
}