package ru.handh.school.igor.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

data class AppTextStyles(
    val text1: TextStyle,
    val text2: TextStyle,
    val text3: TextStyle,
    val text4: TextStyle,
    val text5: TextStyle,
    val text6: TextStyle,
    val text7: TextStyle
)

val defaultTextStyles = AppTextStyles(
    text1 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),
    text2 = TextStyle(
        fontSize = 32.sp,
        lineHeight = 20.sp,
    ),
    text3 = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    text4 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 20.sp
    ),
    text5 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    text6 = TextStyle(
        fontSize = 20.sp,
        lineHeight = 20.sp
    ),
    text7 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
)
