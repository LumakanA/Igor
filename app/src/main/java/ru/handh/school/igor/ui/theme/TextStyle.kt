package ru.handh.school.igor.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

data class AppTextStyles(
    val text1: TextStyle,
    val text2: TextStyle
)

val defaultTextStyles = AppTextStyles(
    text1 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),
    text2 = TextStyle(
        fontSize = 32.sp,
        lineHeight = 20.sp,
    )
)
