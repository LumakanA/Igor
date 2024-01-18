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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.handh.school.igor.domain.projects.getProjectsResponce.Projects
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultIconProjectSize = 40.dp
private val ProjectTextPadding = 5.dp

@Composable
fun ProjectIconWithLetter(
    letter: String
) {
    Box(
        modifier = Modifier
            .size(DefaultIconProjectSize)
            .background(
                color = AppTheme.colors.lightSurface,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = letter,
            style = AppTheme.textStyles.caption.copy(color = AppTheme.colors.darkSurface)
        )
    }
}

@Composable
fun ListProjectItem(
    project: Projects,
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
            ProjectIconWithLetter(letter = project.name?.take(1).orEmpty())
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = AppTheme.offsets.medium,
                    )
            ) {
                BasicText(
                    modifier = Modifier
                        .padding(bottom = ProjectTextPadding),
                    text = project.name.orEmpty(),
                    style = AppTheme.textStyles.highlightedText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                BasicText(
                    text = project.description.orEmpty(),
                    style = AppTheme.textStyles.subtitle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview
@Composable
fun ListProjectItemPreview() {
    val fakeProject = Projects(
        id = "1",
        name = "Проект 1",
        description = "Описание для проекта, может занимать несколько строчек"
    )
    ListProjectItem(project = fakeProject)
}

@Preview
@Composable
fun ListProjectItemPreview1() {
    val fakeProject = Projects(
        id = "2",
        name = "Название 1",
        description = "Описание для проекта, может занимать несколько строчекОписание для проекта, может занимать несколько строчекОписание для проекта, может занимать несколько строчек"
    )
    ListProjectItem(project = fakeProject)
}

@Preview
@Composable
fun ListProjectItemPreview2() {
    val fakeProject = Projects(
        id = "3",
        name = "Sadasdasd",
        description = "sadasdasfdsgfdgfdsgjkfdshjgdsfjgjkfdshkjghjkdfgdflsghsghdjlfhlkgdfslhkghldfkjskhjlsdfghjklsdfgkjhlgfds"
    )
    ListProjectItem(project = fakeProject)
}

@Preview
@Composable
fun ListProjectItemPreview3() {
    val fakeProject = Projects(
        id = "3",
        name = "Sadasdasd",
        description = "sadasdasfdsgfdgfdsgjkfdshjgdsfjgjkfdshkjghjkdfgdflsghsghdjlfhlkgdfslhkghldfkjskhjlsdfghjklsdfgkjhlgfds"
    )
    ListProjectItem(project = fakeProject)
}