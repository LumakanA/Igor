package ru.handh.school.igor.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.data.database.ProfileEntity
import ru.handh.school.igor.ui.components.AppButton
import ru.handh.school.igor.ui.components.ListProfileItem
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultButtonOffset = 52.dp

@Composable
fun ProfileScreen(
    vm: ProfileViewModel,
    navController: NavController
) {

    val state by vm.state.collectAsState()

    ProfileContent(
        state = state,
        onAction = vm::onAction,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
private fun ProfileContent(
    state: ProfileState,
    onAction: (ProfileViewAction) -> Unit = {},
    navController: NavController
) {
    val isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { onAction(ProfileViewAction.LoadProfile) }
    )
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
                        text = stringResource(R.string.profile),
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
                actions = {
                    IconButton(onClick = {
                        navController.navigate("about")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "about"
                        )
                    }
                },
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
            PullRefreshIndicator(
                refreshing = state.profileLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
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
                            loading = state.profileLoading,
                            onClick = {
                                onAction(ProfileViewAction.LoadProfile)
                            },
                            backgroundColor = AppTheme.colors.red
                        )
                    }
                }
            } else if (state.profileLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Column(modifier = Modifier.padding(top = AppTheme.offsets.large)) {
                    ListProfileItem(item = state.itemList?.firstOrNull())
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileContentPreview() {
    val fakeItemList = listOf(
        ProfileEntity(name = "John", surname = "Doe", icon = null),
    )
    ProfileContent(
        state = InitialProfileState.copy(itemList = fakeItemList),
        navController = rememberNavController()
    )
}