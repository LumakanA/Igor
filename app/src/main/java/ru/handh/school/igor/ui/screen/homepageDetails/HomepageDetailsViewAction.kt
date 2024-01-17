package ru.handh.school.igor.ui.screen.homepageDetails

sealed interface HomepageDetailsViewAction {
    data object UploadingData : HomepageDetailsViewAction
}
