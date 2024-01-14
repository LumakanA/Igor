package ru.handh.school.igor.ui.screen.about

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.handh.school.igor.domain.singout.SignOutUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class AboutViewModel(
    private val signOutUseCase: SignOutUseCase
) :
    BaseViewModel<AboutState, AboutViewAction>(InitialAboutState) {
    override fun onAction(action: AboutViewAction) {
        when (action) {
            is AboutViewAction.SubmitClicked -> onSubmitClicked()
        }
    }

    private fun onSubmitClicked() {
        viewModelScope.launch {
            reduceState {
                it.copy(aboutLoading = true)
            }
            signOutUseCase.execute()
            reduceState {
                it.copy(aboutLoading = false)
            }
        }
    }
}