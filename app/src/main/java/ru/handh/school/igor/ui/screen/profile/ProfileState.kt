package ru.handh.school.igor.ui.screen.profile

import ru.handh.school.igor.data.database.ProfileEntity

val InitialProfileState = ProfileState(
    profileButtonLoading = false,
    profileLoading = false,
    error = false,
    errorMessage = null,
    itemList = emptyList()
)

data class ProfileState(
    val profileButtonLoading: Boolean,
    val profileLoading: Boolean,
    val error: Boolean,
    val errorMessage: String?,
    val itemList: List<ProfileEntity>?
)
