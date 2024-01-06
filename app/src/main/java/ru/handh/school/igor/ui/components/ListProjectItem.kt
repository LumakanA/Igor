package ru.handh.school.igor.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.handh.school.igor.domain.projects.getProjectsResponce.ProjectsData
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultIconProjectSize = 40.dp

@Composable
fun ProfileIconWithLetter(
    letter: String,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .size(DefaultIconProjectSize)
            .background(
                color = AppTheme.colors.lightSurface,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = letter,
            style = AppTheme.textStyles.text7.copy(color = AppTheme.colors.darkSurface)
        )
    }
}

@Composable
fun ListProjectItem(
    project: ProjectsData,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(
                start = AppTheme.offsets.medium,
                end = AppTheme.offsets.large,
                top = AppTheme.offsets.small,
                bottom = AppTheme.offsets.small
            )
            .clickable { onClick() }
    ) {
        Row {
            val firstProject = project.data?.projects?.firstOrNull()
            if (firstProject != null) {
                ProfileIconWithLetter(letter = firstProject.name?.take(1) ?: "")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = AppTheme.offsets.medium,
                        )
                ) {
                    BasicText(
                        modifier = Modifier
                            .padding(bottom = 5.dp),
                        text = firstProject.name ?: "",
                        style = AppTheme.textStyles.text7
                    )
                    BasicText(
                        text = firstProject.description ?: "",
                        style = AppTheme.textStyles.text5
                    )
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun ListProjectItemPreview() {
//    val fakeProject = Projects(name = "Проект 1", description = "Описание для проекта, может занимать несколько строчек")
//    ListProjectItem(project = fakeProject)
//}
//
//@Preview
//@Composable
//fun ListProjectItemPreview1() {
//    val fakeProject = Projects(name = "Название 1", description = "Описание для проекта, может занимать несколько строчекОписание для проекта, может занимать несколько строчекОписание для проекта, может занимать несколько строчек")
//    ListProjectItem(project = fakeProject)
//}
//
//@Preview
//@Composable
//fun ListProjectItemPreview2() {
//    val fakeProject = Projects(name = "Sadasdasd", description = "sadasdasfdsgfdgfdsgjkfdshjgdsfjgjkfdshkjghjkdfgdflsghsghdjlfhlkgdfslhkghldfkjskhjlsdfghjklsdfgkjhlgfds")
//    ListProjectItem(project = fakeProject)
//}