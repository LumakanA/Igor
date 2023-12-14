package ru.handh.school.igor.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultContainerHeight = 56.dp
private val DefaultBorderSize = 1.dp

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
) {
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        modifier = modifier
            .border(
                DefaultBorderSize, if (isFocused) {
                    AppTheme.colors.primary
                } else {
                    AppTheme.colors.grey
                },
                shape = AppTheme.roundings.large
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        value = value,
        onValueChange = { onValueChange(it) },
        textStyle = AppTheme.textStyles.text1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .clip(AppTheme.roundings.large)
                    .height(DefaultContainerHeight)
                    .background(AppTheme.colors.surfaceBright)
                    .padding(
                        vertical = AppTheme.offsets.small,
                        horizontal = AppTheme.offsets.emailOffset
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicText(
                    text = if (value.isEmpty()) hint else "",
                    style = AppTheme.textStyles.text1.copy(color = Color.Gray),
                )
                innerTextField()
            }
        }
    )
}


@Preview
@Composable
fun AppTextFieldPreview() {
    AppTextField(
        modifier = Modifier.fillMaxWidth(),
        value = stringResource(R.string.email),
        onValueChange = { },
        hint = "EmailTextField"
    )
}