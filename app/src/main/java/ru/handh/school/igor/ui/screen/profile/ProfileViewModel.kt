package ru.handh.school.igor.ui.screen.profile

import ru.handh.school.igor.ui.base.BaseViewModel

class ProfileViewModel :
    BaseViewModel<ProfileState, ProfileViewAction>(InitialProfileState) {
    override fun onAction(action: ProfileViewAction) {
        when (action) {
            is ProfileViewAction.SubmitClicked -> onSubmitClicked()
            is ProfileViewAction.UpdateToken -> onUpdateToken(action.token)
        }
    }

    private fun onSubmitClicked() {
    }

    private fun onUpdateToken(token: String) {
        TODO()
    }
}