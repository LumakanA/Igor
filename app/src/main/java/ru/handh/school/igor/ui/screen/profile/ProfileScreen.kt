package ru.handh.school.igor.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        text = stringResource(R.string.about),
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
                Row {
                    Image(
                        modifier = Modifier.size(82.dp, 82.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo"
                    )
                    Column(modifier = Modifier.padding(start = AppTheme.offsets.medium)) {
                        BasicText(
                            text = stringResource(R.string.igor),
                            style = AppTheme.textStyles.text4
                        )

                        BasicText(
                            modifier = Modifier.padding(top = AppTheme.offsets.small),
                            text = stringResource(R.string.version),
                            style = AppTheme.textStyles.text5
                        )

                        BasicText(
                            text = stringResource(R.string.build_number),
                            style = AppTheme.textStyles.text5
                        )
                    }
                }
            }
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.offsets.medium)
                    .align(Alignment.BottomCenter),
                label = stringResource(R.string.exitButton),
                loading = state.ProfileLoading,
                onClick = {
                    onAction(ProfileViewAction.SubmitClicked)
                    navController.navigate("signin")
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