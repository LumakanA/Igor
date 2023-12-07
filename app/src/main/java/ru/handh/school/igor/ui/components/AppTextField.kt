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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.handh.school.igor.R
import ru.handh.school.igor.ui.theme.AppTheme

private val DefaultContainerHeight = 56.dp

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        textStyle = AppTheme.textStyles.text1,
        modifier = modifier,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .clip(AppTheme.roundings.large)
                    .fillMaxWidth()
                    .height(DefaultContainerHeight)
                    .background(AppTheme.colors.textOnControl)
                    .border(1.dp, AppTheme.colors.borderGrey, shape = AppTheme.roundings.large)
                    .padding(
                        vertical = AppTheme.offsets.small,
                        horizontal = AppTheme.offsets.emailOffset
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicText(
                    text = if (value.isEmpty()) label else "",
                    style = AppTheme.textStyles.text1.copy(color = Color.Gray),
                    modifier = Modifier.padding(horizontal = AppTheme.offsets.small)
                )
                innerTextField()
            }
        }
    )
}



@Preview
@Composable
fun AppTextFieldPreview() {
    AppTheme {
        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = stringResource(R.string.email),
            onValueChange = { },
            label = "EmailTextField"
        )
    }
}
