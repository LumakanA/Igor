package ru.handh.school.igor.ui.screen.profile

sealed interface ProfileViewAction {
    data object SubmitClicked : ProfileViewAction
}