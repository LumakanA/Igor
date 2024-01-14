package ru.handh.school.igor.ui.screen.profile

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.handh.school.igor.data.database.ProfileDao
import ru.handh.school.igor.domain.profile.ProfileUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class ProfileViewModel(
    private val profileUseCase: ProfileUseCase,
    private val profileDao: ProfileDao
) : BaseViewModel<ProfileState, ProfileViewAction>(InitialProfileState) {

    init {
        viewModelScope.launch {
            onLoadProfile()
        }
    }

    override fun onAction(action: ProfileViewAction) {
        when (action) {
            is ProfileViewAction.LoadProfile -> onLoadProfile()
        }
    }

    private fun onLoadProfile() {
        viewModelScope.launch {
            try {
                reduceState {
                    it.copy(
                        profileLoading = true,
                        profileButtonLoading = true,
                        error = false,
                        errorMessage = null,
                        itemList = emptyList()
                    )
                }
                profileDao.deleteAll()
                val loadedData = profileUseCase.execute()
                if (loadedData.isNotEmpty()) {
                    reduceState {
                        it.copy(
                            profileLoading = false,
                            profileButtonLoading = false,
                            error = false,
                            errorMessage = null,
                            itemList = loadedData
                        )
                    }
                } else {
                    reduceState {
                        it.copy(
                            error = true,
                            profileLoading = false,
                            profileButtonLoading = false,
                            itemList = emptyList()
                        )
                    }
                }
            } catch (e: Exception) {
                reduceState {
                    it.copy(
                        error = true,
                        profileLoading = false,
                        profileButtonLoading = false,
                        errorMessage = e.message,
                        itemList = emptyList()
                    )
                }
            }
        }
    }
}



