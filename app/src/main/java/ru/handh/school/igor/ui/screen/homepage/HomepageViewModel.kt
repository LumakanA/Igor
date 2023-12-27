package ru.handh.school.igor.ui.screen.homepage

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.handh.school.igor.ui.base.BaseViewModel

class HomepageViewModel() :
    BaseViewModel<HomepageState, HomepageViewAction>(InitialHomepageState) {
    override fun onAction(action: HomepageViewAction) {
        when (action) {
            HomepageViewAction.SubmitClicked -> onSubmitClicked()
        }
    }

    private fun onSubmitClicked() {
        viewModelScope.launch {
            reduceState {
                it.copy(aboutLoading = true)
            }
            reduceState {
                it.copy(aboutLoading = false)
            }
        }
    }
}
