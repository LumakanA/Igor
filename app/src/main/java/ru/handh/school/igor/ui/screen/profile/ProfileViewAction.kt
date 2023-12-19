package ru.handh.school.igor.ui.screen.profile

sealed interface ProfileViewAction {
    data object SubmitClicked : ProfileViewAction
    data class UpdateToken(val token: String) : ProfileViewAction
}