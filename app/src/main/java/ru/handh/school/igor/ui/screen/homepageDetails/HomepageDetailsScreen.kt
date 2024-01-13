package ru.handh.school.igor.ui.screen.homepageDetails

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.screen.homepage.HomepageViewModel
import ru.handh.school.igor.ui.theme.AppTheme


@Composable
fun ProjectDetailScreen(
    vm: HomepageViewModel,
    navController: NavController,
    context: Context
) {
    val state by vm.state.collectAsState()
    ProjectDetailContent(
        navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailContent(
    navController: NavController,
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
                        text = stringResource(R.string.profile),
                        style = AppTheme.textStyles.text3,
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
            contentAlignment = Alignment.TopCenter
        ) {

        }
    }
}

@Preview
@Composable
fun ProjectDetailScreenPreview() {
    ProjectDetailContent(navController = rememberNavController())
}
