package ru.handh.school.igor.ui.screen.homepage

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.handh.school.igor.domain.projects.ProjectsUseCase
import ru.handh.school.igor.domain.results.ResultProjects
import ru.handh.school.igor.ui.base.BaseViewModel

class HomepageViewModel(private val projectsUseCase: ProjectsUseCase) :
    BaseViewModel<HomepageState, HomepageViewAction>(InitialHomepageState) {
    private val resultProjectsChannel = Channel<ResultProjects<Unit>>()
    val projectsResult = resultProjectsChannel.receiveAsFlow()
    override fun onAction(action: HomepageViewAction) {
        when (action) {
            HomepageViewAction.ReloadClicked -> onReloadClicked()
            is HomepageViewAction.ProjectClicked -> onProjectClicked()
        }
    }

    init {
        onReloadClicked()
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

                if (projects.data.isNullOrEmpty()) {
                    reduceState {
                        it.copy(
                            error = true,
                            homepageLoading = false,
                            homepageButtonLoading = false,
                        )
                    }
                } else {
                    reduceState {
                        it.copy(
                            projects = projects.data,
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


    private fun onProjectClicked() {
        //TODO()
    }
}
