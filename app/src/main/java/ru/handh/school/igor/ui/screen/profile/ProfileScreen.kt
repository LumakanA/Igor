package ru.handh.school.igor.ui.screen.profile

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.components.AppButton
import ru.handh.school.igor.ui.theme.AppTheme


@Composable
fun ProfileScreen(
    vm: ProfileViewModel,
    navController: NavController
) {
    val state by vm.state.collectAsState()
    ProfileContent(
        state = state,
        onAction = vm::onAction,
        navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileContent(
    state: ProfileState,
    onAction: (ProfileViewAction) -> Unit = {},
    navController: NavController,
    context: Context = LocalContext.current
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        text = stringResource(R.string.profile),
                        style = AppTheme.textStyles.text3,
                    )
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.offsets.medium)
            ) {
            }
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.offsets.medium)
                    .align(Alignment.BottomCenter),
                label = stringResource(R.string.about),
                loading = state.profileLoading,
                onClick = {
                    onAction(ProfileViewAction.SubmitClicked)
                    navController.navigate("about")
                },
                backgroundColor = AppTheme.colors.red
            )
        }
    }
}

@Preview
@Composable
private fun ProfileContentPreview() {
    ProfileContent(
        state = InitialProfileState,
        navController = rememberNavController()
    )
}