package ru.handh.school.igor.ui.screen.homepage

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.domain.results.ResultProjects
import ru.handh.school.igor.ui.components.AppButton
import ru.handh.school.igor.ui.components.ListProjectItem
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultButtonOffset = 52.dp

@Composable
fun HomepageScreen(
    vm: HomepageViewModel,
    navController: NavController,
    context: Context
) {
    LaunchedEffect(vm, context) {
        vm.projectsResult.collect { result ->
            when (result) {
                is ResultProjects.ReceivedProjects -> Log.d("ProjectsCollect", "1")
                is ResultProjects.ReceivedProjectsDetails -> Log.d("ProjectsCollect", "2")
                is ResultProjects.UnknownError -> {
                    Log.d("ProjectsCollect", "Ошибка при загрузке проектов")
                    Toast.makeText(context, R.string.error_occurred, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    val state by vm.state.collectAsState()
    HomepageContent(
        state = state,
        onAction = vm::onAction,
        navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomepageContent(
    state: HomepageState,
    onAction: (HomepageViewAction) -> Unit = {},
    navController: NavController,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        text = stringResource(R.string.projects),
                        style = AppTheme.textStyles.text3
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
                .background(color = AppTheme.colors.surface),
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
                            .align(Alignment.TopCenter),
                    ) {
                        BasicText(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = stringResource(R.string.something_went_wrong),
                            style = AppTheme.textStyles.text8
                        )
                        BasicText(
                            modifier = Modifier
                                .padding(top = AppTheme.offsets.large, bottom = DefaultButtonOffset)
                                .align(Alignment.CenterHorizontally),
                            text = state.errorMessage
                                ?: stringResource(R.string.unknown_error_has_occurred),
                            style = AppTheme.textStyles.text9
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.offsets.medium)
                ) {
                    for (item in state.projects) {
                        ListProjectItem(project = item)
                    }
                }
            }
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