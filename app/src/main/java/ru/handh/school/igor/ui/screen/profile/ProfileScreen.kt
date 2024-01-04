package ru.handh.school.igor.ui.screen.profile

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.handh.school.igor.R
import ru.handh.school.igor.data.database.ProfileEntity
import ru.handh.school.igor.ui.components.ListItem
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultProgressIndicatorSize = 18.dp
private val DefaultProgressIndicatorStrokeWidth = 2.dp
private val DefaultTextOffset = 87.dp

@Composable
fun ProfileScreen(
    vm: ProfileViewModel,
    navController: NavController
) {
    val state by vm.state.collectAsState()
    val itemList by vm.itemsList.collectAsState(initial = emptyList())
    ProfileContent(
        state = state,
        onAction = vm::onAction,
        navController = navController,
        itemList = itemList
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileContent(
    state: ProfileState,
    onAction: (ProfileViewAction) -> Unit = {},
    navController: NavController,
    itemList: List<ProfileEntity>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
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
                    .padding(top = AppTheme.offsets.large)
                    .fillMaxSize()
            ) {
                for (item in itemList) {
                    ListItem(item = item)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileContentPreview() {
    val fakeItemList = listOf(
        ProfileEntity(name = "John", surname = "Doe"),
    )
    ProfileContent(
        state = InitialProfileState,
        navController = rememberNavController(),
        itemList = fakeItemList
    )
}