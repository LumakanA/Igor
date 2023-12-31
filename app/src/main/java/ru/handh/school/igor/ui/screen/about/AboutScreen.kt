package ru.handh.school.igor.ui.screen.about

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.components.AppButton
import ru.handh.school.igor.ui.theme.AppTheme
import ru.handh.school.igor.utils.versionCode
import ru.handh.school.igor.utils.versionName


@Composable
fun AboutScreen(
    vm: AboutViewModel,
    navController: NavController
) {
    val state by vm.state.collectAsState()
    AboutContent(
        state = state,
        onAction = vm::onAction,
        navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AboutContent(
    state: AboutState,
    onAction: (AboutViewAction) -> Unit = {},
    navController: NavController,
    context: Context = LocalContext.current
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        text = stringResource(R.string.about),
                        style = AppTheme.textStyles.text3,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("profile")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "back"
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.offsets.medium)
            ) {
                Row {
                    Image(
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
                            text = stringResource(R.string.version, context.versionName),
                            style = AppTheme.textStyles.text5
                        )

                        BasicText(
                            text = stringResource(R.string.build_number, context.versionCode),
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
                loading = state.aboutLoading,
                onClick = {
                    onAction(AboutViewAction.SubmitClicked)
                    navController.navigate("signIn")
                },
                backgroundColor = AppTheme.colors.red
            )
        }
    }
}

@Preview
@Composable
private fun AboutContentPreview() {
    AboutContent(
        state = InitialAboutState,
        navController = rememberNavController()
    )
}