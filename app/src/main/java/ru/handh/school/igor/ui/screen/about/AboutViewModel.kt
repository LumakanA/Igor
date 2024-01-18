package ru.handh.school.igor.ui.screen.about

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.handh.school.igor.data.KeyValueStorage
import ru.handh.school.igor.domain.results.ResultProfile
import ru.handh.school.igor.domain.singout.SignOutUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class AboutViewModel(
    private val signOutUseCase: SignOutUseCase,
    private val storage: KeyValueStorage
) :
    BaseViewModel<AboutState, AboutViewAction>(InitialAboutState) {
    private val resultProfileChannel = Channel<ResultProfile<Unit>>()
    val loggedOutResult = resultProfileChannel.receiveAsFlow()
    override fun onAction(action: AboutViewAction) {
        when (action) {
            is AboutViewAction.SignOut -> onSignOut()
        }
    }

    private fun onSignOut() {
        viewModelScope.launch {
            try {
                reduceState {
                    it.copy(aboutLoading = true)
                }
                val resultLoggedOut = signOutUseCase.execute()
                resultProfileChannel.send(resultLoggedOut)
                when (resultLoggedOut) {
                    is ResultProfile.LoggedOut -> {
                        reduceState {
                            it.copy(aboutLoading = false)
                        }
                        storage.accessToken = null
                        storage.refreshToken = null
                    }
                    is ResultProfile.UnknownError -> {
                        reduceState {
                            it.copy(aboutLoading = false)
                        }
                    }
                }
            } catch (e: Exception) {
                reduceState {
                    it.copy(aboutLoading = false)
                }
            }
        }
    }
}