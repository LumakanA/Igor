package ru.handh.school.igor.ui.screen.about

sealed interface AboutViewAction {
    data object SignOut : AboutViewAction
}