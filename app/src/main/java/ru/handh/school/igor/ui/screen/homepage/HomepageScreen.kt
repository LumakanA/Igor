package ru.handh.school.igor.ui.screen.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.components.AppButton
import ru.handh.school.igor.ui.components.ListProjectItem
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultButtonOffset = 52.dp

@Composable
fun HomepageScreen(
    vm: HomepageViewModel,
    navController: NavController
) {
    val state by vm.state.collectAsState()
    HomepageContent(
        state = state,
        onAction = vm::onAction,
        navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
private fun HomepageContent(
    state: HomepageState,
    onAction: (HomepageViewAction) -> Unit = {},
    navController: NavController,
) {
    val isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { onAction(HomepageViewAction.ReloadClicked) }
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        text = stringResource(R.string.projects),
                        style = AppTheme.textStyles.heading2
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("profile")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "profile"
                        )
                    }
                },
                modifier = Modifier
                    .background(color = AppTheme.colors.surfaceBright),
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = AppTheme.colors.surface)
                .pullRefresh(pullRefreshState),
            contentAlignment = Alignment.TopCenter
        ) {
            if (state.error) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppTheme.colors.surface),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 200.dp)
                            .navigationBarsPadding()
                            .align(Alignment.TopCenter),
                    ) {
                        BasicText(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = stringResource(R.string.something_went_wrong),
                            style = AppTheme.textStyles.largeText
                        )
                        BasicText(
                            modifier = Modifier
                                .padding(top = AppTheme.offsets.large, bottom = DefaultButtonOffset)
                                .align(Alignment.CenterHorizontally),
                            text = state.errorMessage
                                ?: stringResource(R.string.unknown_error_has_occurred),
                            style = AppTheme.textStyles.smallText
                        )
                        AppButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppTheme.offsets.medium),
                            label = stringResource(R.string.retry),
                            loading = state.homepageButtonLoading,
                            onClick = {
                                onAction(HomepageViewAction.ReloadClicked)
                            },
                            backgroundColor = AppTheme.colors.red
                        )
                    }
                }
            } else if (state.homepageLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    items(state.projects) { project ->
                        ListProjectItem(
                            project = project,
                            onClick = {
                                navController.navigate("projectDetails")
                                onAction(
                                    HomepageViewAction.ProjectClicked(project.id.orEmpty())
                                )
                            },
                        )
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = state.homepageLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}


@Preview
@Composable
private fun HomepageContentPreview() {
    HomepageContent(
        state = InitialHomepageState,
        navController = rememberNavController()
    )
}