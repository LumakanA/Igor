package ru.handh.school.igor.ui.screen.homepage

sealed interface HomepageViewAction {
    data object ReloadClicked : HomepageViewAction
    data class ProjectClicked(val id: String) : HomepageViewAction
}
