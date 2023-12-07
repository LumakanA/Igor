package ru.handh.school.igor.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class AppRoundings(
    val small: RoundedCornerShape,
    val large: RoundedCornerShape
)

val defaultRoundings = AppRoundings(
    small = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)
