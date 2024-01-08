package ru.handh.school.igor.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppOffsets(
    val tiny: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val huge: Dp
)

val defaultOffsets = AppOffsets(
    tiny = 8.dp,
    small = 12.dp,
    medium = 16.dp,
    large = 24.dp,
    huge = 32.dp
)
