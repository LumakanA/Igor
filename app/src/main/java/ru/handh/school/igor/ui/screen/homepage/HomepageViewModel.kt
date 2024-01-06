package ru.handh.school.igor.ui.screen.homepage

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.handh.school.igor.domain.projects.ProjectsUseCase
import ru.handh.school.igor.ui.base.BaseViewModel

class HomepageViewModel(private val projectsUseCase: ProjectsUseCase) :
    BaseViewModel<HomepageState, HomepageViewAction>(InitialHomepageState) {
    override fun onAction(action: HomepageViewAction) {
        when (action) {
            HomepageViewAction.SubmitClicked -> onSubmitClicked()
        }
    }

    init {
        loadProjects()
    }

    private fun onSubmitClicked() {
    }

    private fun loadProjects() {
        viewModelScope.launch {
            try {
                val projects = projectsUseCase.execute()
                reduceState { currentState ->
                    currentState.copy(projects = projects, aboutLoading = false)
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

}
