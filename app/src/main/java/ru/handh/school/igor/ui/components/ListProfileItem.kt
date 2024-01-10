package ru.handh.school.igor.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.handh.school.igor.R
import ru.handh.school.igor.data.database.ProfileEntity
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultIconProfileSize = 84.dp

@Composable
fun ListItem(
    item: ProfileEntity
) {
    Box() {
        Row {
            Image(
                modifier = Modifier
                    .size(DefaultIconProfileSize)
                    .padding(start = AppTheme.offsets.medium),
                painter = painterResource(id = R.drawable.icon_profile),
                contentDescription = "iconProfile"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = AppTheme.offsets.medium,
                    )
            ) {

                BasicText(
                    text = item.name,
                    style = AppTheme.textStyles.text2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                BasicText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = item.surname,
                    style = AppTheme.textStyles.text6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun ListItemPreview() {
    val previewItem =
        ProfileEntity(name = "Johnnnnnnnnnnnnnnnnnnnnn", surname = "Doeeeeeeeeeeeeeeeeeee")
    ListItem(previewItem)
}