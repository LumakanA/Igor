package ru.handh.school.igor.ui.screen.profile

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.handh.school.igor.domain.singout.SignOutUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class ProfileViewModel(
    private val signOutUseCase: SignOutUseCase
) :
    BaseViewModel<ProfileState, ProfileViewAction>(InitialProfileState) {
    override fun onAction(action: ProfileViewAction) {
        when (action) {
            ProfileViewAction.SubmitClicked -> onSubmitClicked()
        }
    }

    private fun onSubmitClicked() {
        viewModelScope.launch {
            signOutUseCase.execute()
        }
    }
}