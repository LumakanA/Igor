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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.handh.school.igor.ui.theme.AppTheme

const val firstPriority = "High"
const val secondPriority = "Medium"
const val thirdPriority = "Low"
private val circularSize = 16.dp

@Composable
fun ProjectTask(
    task: String,
    priority: String
) {
    val colorTask: Color = when (priority) {
        firstPriority -> AppTheme.colors.red
        secondPriority -> AppTheme.colors.yellow
        thirdPriority -> AppTheme.colors.green
        else -> throw IllegalArgumentException("Unexpected priority value: $priority")
    }

    Row(modifier = Modifier.padding(vertical = AppTheme.offsets.medium),
        verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(circularSize)
                .clip(CircleShape)
                .background(colorTask)
                .padding(horizontal = AppTheme.offsets.medium)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = AppTheme.offsets.medium,
                )
        ) {
            BasicText(
                text = task,
                style = AppTheme.textStyles.highlightedText,
            )
        }
    }
}

@Preview
@Composable
fun ProjectTask() {
    ProjectTask("Создать проект", firstPriority)
}

