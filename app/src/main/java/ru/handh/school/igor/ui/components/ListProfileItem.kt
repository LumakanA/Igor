package ru.handh.school.igor.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.handh.school.igor.R
import ru.handh.school.igor.data.database.ProfileEntity
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultIconProfileSize = 78.dp

@Composable
fun ProfileIconWithLetter(
    letter: String,
) {
    Column(modifier = Modifier.padding(start = AppTheme.offsets.medium))
    {
        Box(
            modifier = Modifier
                .size(DefaultIconProfileSize)
                .background(
                    color = AppTheme.colors.lightSurface,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            BasicText(
                text = letter,
                style = AppTheme.textStyles.heading1.copy(color = AppTheme.colors.darkSurface)
            )
        }
    }
}

@Composable
fun ListProfileItem(
    item: ProfileEntity?
) {
    Box {
        Row {
            val letterIcon =
                item?.name?.firstOrNull().toString() + item?.surname?.firstOrNull().toString()
            if (item?.icon != null && item.icon.isNotEmpty()) {
                AsyncImage(
                    modifier = Modifier.clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.icon)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.icon_profile),
                    contentDescription = stringResource(id = R.string.icon_profile),
                    contentScale = ContentScale.Crop,
                )
            } else {
                ProfileIconWithLetter(letter = letterIcon)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = AppTheme.offsets.medium,
                    )
            ) {
                BasicText(
                    text = item?.name.orEmpty(),
                    style = AppTheme.textStyles.heading1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                BasicText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = item?.surname.orEmpty(),
                    style = AppTheme.textStyles.body,
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
        ProfileEntity(name = "Johnnnnnnnnnnnnnnnnnnnnn", surname = "Doeeeeeeeeeeeeeeeeeee", icon = null)
    ListProfileItem(previewItem)
}

@Preview
@Composable
fun ListItemPreview1() {
    val previewItem =
        ProfileEntity(name = "Константин", surname = "Архангельский-Прохоров", icon = null)
    ListProfileItem(previewItem)
}