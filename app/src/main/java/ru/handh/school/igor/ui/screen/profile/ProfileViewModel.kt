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
) :
    BaseViewModel<ProfileState, ProfileViewAction>(InitialProfileState) {
    init {
        onSubmitClicked()
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
                val profileData = profileUseCase.execute()
                val profile = profileData.data?.profile
                profile?.let {
                    val profileEntity = ProfileEntity(
                        name = it.name ?: "",
                        surname = it.surname ?: ""
                    )
                    database.profileDao.insertProfile(profileEntity)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}