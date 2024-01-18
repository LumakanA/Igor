package ru.handh.school.igor.ui.screen.homepageDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.components.AppButton
import ru.handh.school.igor.ui.components.ProjectTask
import ru.handh.school.igor.ui.components.firstPriority
import ru.handh.school.igor.ui.components.secondPriority
import ru.handh.school.igor.ui.components.thirdPriority
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultButtonOffset = 52.dp

@Composable
fun HomepageDetailsScreen(
    vm: HomepageDetailsViewModel,
    navController: NavController
) {
    val state by vm.state.collectAsState()
    HomepageDetailsContent(
        navController,
        onAction = vm::onAction,
        state,
        tasks = listOf(
            "Создать проект" to firstPriority,
            "Переделать проект" to secondPriority,
            "Опубликовать проект" to thirdPriority,
            "Исправить баг" to firstPriority,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageDetailsContent(
    navController: NavController,
    onAction: (HomepageDetailsViewAction) -> Unit = {},
    state: HomepageDetailsState,
    tasks: List<Pair<String, String>>
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .background(color = AppTheme.colors.surfaceBright),
                title = {
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        text = state.projectsDetails?.name.orEmpty(),
                        style = AppTheme.textStyles.heading2,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("homepage")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "back"
                        )
                    }
                },
            )
        }
    )
    { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = AppTheme.colors.surface),
        ) {
            if (state.error) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppTheme.colors.surface)
                ) {
                    val quarterScreenHeight = LocalConfiguration.current.screenHeightDp.dp / 4
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = quarterScreenHeight),
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
                                onAction(HomepageDetailsViewAction.UploadingData)
                            },
                            backgroundColor = AppTheme.colors.red
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.offsets.medium)
                ) {
                    BasicText(
                        text = state.projectsDetails?.description.orEmpty(),
                        style = AppTheme.textStyles.mediumText
                    )
                    tasks.forEach { (task, priority) ->
                        ProjectTask(task = task, priority = priority)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomepageDetailsScreenPreview() {
    HomepageDetailsContent(
        navController = rememberNavController(),
        state = InitialHomepageDetailsState,
        tasks = listOf(
            "Создать проект" to firstPriority,
            "Переделать проект" to secondPriority,
            "Опубликовать проект" to thirdPriority,
            "Исправить баг" to firstPriority,
        )
    )
}
