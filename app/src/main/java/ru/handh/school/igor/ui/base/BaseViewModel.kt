package ru.handh.school.igor.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S, VA>(
    initialState: S
) : ViewModel(), StateHolder<S>, ViewActionHandler<VA> {

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<S> = _state

    protected fun reduceState(reducer: (S) -> S) {
        _state.value = reducer.invoke(_state.value)
    }
}
