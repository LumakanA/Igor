package ru.handh.school.igor.ui.screen.homepage

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ru.handh.school.igor.data.GlobalData
import ru.handh.school.igor.domain.projects.ProjectsUseCase
import ru.handh.school.igor.domain.results.ResultProjects
import ru.handh.school.igor.ui.base.BaseViewModel

class HomepageViewModel(
    private val sharedViewModel: GlobalData,
    private val projectsUseCase: ProjectsUseCase
) :
    BaseViewModel<HomepageState, HomepageViewAction>(InitialHomepageState) {
    private val resultProjectsChannel = Channel<ResultProjects<Unit>>()
    private var isInitialized = false
    override fun onAction(action: HomepageViewAction) {
        when (action) {
            is HomepageViewAction.ReloadClicked -> onReloadClicked()
            is HomepageViewAction.ProjectClicked -> onProjectClicked(action.id)
        }
    }

    init {
        if (!isInitialized) {
            onReloadClicked()
            isInitialized = true
        }
    }

    private fun onReloadClicked() {
        viewModelScope.launch {
            try {
                reduceState {
                    it.copy(
                        homepageLoading = true,
                        homepageButtonLoading = true,
                        error = false,
                        errorMessage = null
                    )
                }
                val projects = projectsUseCase.execute()
                val newProjects = projects.data?.data?.projects.orEmpty()
                if (newProjects.isEmpty()) {
                    reduceState {
                        it.copy(
                            error = true,
                            homepageLoading = false,
                            homepageButtonLoading = false,
                        )
                    }
                } else {
                    reduceState { currentState ->
                        currentState.copy(
                            projects = if (projects.data?.data?.hasMore == false) {
                                newProjects
                            } else {
                                currentState.projects + newProjects
                            },
                            homepageLoading = false,
                            homepageButtonLoading = false,
                            error = false,
                            errorMessage = null
                        )
                    }
                }
            } catch (e: Exception) {
                resultProjectsChannel.send(ResultProjects.UnknownError())
                reduceState {
                    it.copy(
                        error = true,
                        homepageLoading = false,
                        homepageButtonLoading = false,
                        errorMessage = e.message
                    )
                }
            }
        }
    }


    private fun onProjectClicked(id: String) {
        viewModelScope.launch {
            try {
                sharedViewModel.setSelectedProjectId(id)
                reduceState {
                    it.copy(
                        homepageLoading = true,
                        homepageButtonLoading = true,
                        error = false,
                        errorMessage = null,
                        selectedProjectId = id
                    )
                }
            } catch (e: Exception) {
                resultProjectsChannel.send(ResultProjects.UnknownError())
                reduceState {
                    it.copy(
                        error = true,
                        homepageLoading = false,
                        homepageButtonLoading = false,
                        errorMessage = e.message
                    )
                }
            }
        }
    }
}
