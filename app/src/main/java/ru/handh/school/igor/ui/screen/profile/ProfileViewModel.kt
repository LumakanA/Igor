package ru.handh.school.igor.ui.screen.profile

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.handh.school.igor.data.database.MainDb
import ru.handh.school.igor.data.database.ProfileEntity
import ru.handh.school.igor.domain.profile.ProfileUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase,
    private val database: MainDb
) : BaseViewModel<ProfileState, ProfileViewAction>(InitialProfileState) {

    init {
        viewModelScope.launch {
            onSubmitClicked()
        }
    }

    override fun onAction(action: ProfileViewAction) {
        when (action) {
            ProfileViewAction.SubmitClicked -> onSubmitClicked()
        }
    }

    val itemsList: Flow<List<ProfileEntity>> = database.profileDao.getProfile()

    private fun onSubmitClicked() {
        viewModelScope.launch {
            try {
                reduceState {
                    it.copy(
                        profileLoading = true,
                        profileButtonLoading = true,
                        error = false,
                        errorMessage = null
                    )
                }

                database.profileDao.deleteAll()
                profileUseCase.execute()
                reduceState {
                    it.copy(
                        profileLoading = false,
                        profileButtonLoading = false,
                        error = false,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                reduceState {
                    it.copy(
                        error = true,
                        profileLoading = false,
                        profileButtonLoading = false,
                        errorMessage = e.message
                    )
                }
            }
        }
    }
}


