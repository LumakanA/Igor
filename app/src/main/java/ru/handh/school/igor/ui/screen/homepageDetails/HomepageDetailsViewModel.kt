package ru.handh.school.igor.ui.screen.homepageDetails

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ru.handh.school.igor.data.GlobalData
import ru.handh.school.igor.domain.projectDetails.ProjectDetailsUseCase
import ru.handh.school.igor.domain.results.ResultProjects
import ru.handh.school.igor.ui.base.BaseViewModel

class HomepageDetailsViewModel(
    private val sharedViewModel: GlobalData,
    private val projectDetailsUseCase: ProjectDetailsUseCase
) :
    BaseViewModel<HomepageDetailsState, HomepageDetailsViewAction>(InitialHomepageDetailsState) {
    private val resultProjectsChannel = Channel<ResultProjects<Unit>>()
    override fun onAction(action: HomepageDetailsViewAction) {
        when (action) {
            is HomepageDetailsViewAction.UploadingData -> onUploadingData()
        }
    }

    init {
        onUploadingData()
    }

    private fun onUploadingData() {
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

                val projectId: String? = sharedViewModel.getSelectedProjectId().value

                if (projectId != null) {
                    val projectDetails = projectDetailsUseCase.execute(projectId)

                    if (projectDetails.data == null || projectDetails.data.data.toString().isEmpty()) {
                        reduceState {
                            it.copy(
                                homepageLoading = false,
                                homepageButtonLoading = false,
                                error = true,
                                errorMessage = "Empty project details"
                            )
                        }
                    } else {
                        reduceState {
                            it.copy(
                                projectsDetails = projectDetails.data.data,
                                homepageLoading = false,
                                homepageButtonLoading = false,
                                error = false,
                                errorMessage = null
                            )
                        }
                    }
                } else {
                    reduceState {
                        it.copy(
                            homepageLoading = false,
                            homepageButtonLoading = false,
                            error = true,
                            errorMessage = "Project ID is null"
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
}
