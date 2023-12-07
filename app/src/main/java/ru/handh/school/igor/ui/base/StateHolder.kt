package ru.handh.school.igor.ui.base

import kotlinx.coroutines.flow.StateFlow

interface StateHolder<S> {
    val state: StateFlow<S>
}
