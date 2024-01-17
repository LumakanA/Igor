package ru.handh.school.igor.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object GlobalData {
    private val _selectedProjectId = MutableStateFlow<String?>(null)

    fun setSelectedProjectId(id: String) {
        _selectedProjectId.value = id
    }

    fun getSelectedProjectId(): StateFlow<String?> {
        return _selectedProjectId
    }
}

