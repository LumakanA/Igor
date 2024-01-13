package ru.handh.school.igor.domain.results

sealed class ResultProjects<T>(val data: T? = null) {
    class ReceivedProjects<T>(data: T? = null) : ResultProjects<T>(data)
    class ReceivedProjectsDetails<T>(data: T? = null) : ResultProjects<T>()
    class UnknownError<T> : ResultProjects<T>()
}