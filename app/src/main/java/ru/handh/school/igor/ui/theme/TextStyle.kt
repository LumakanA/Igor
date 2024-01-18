package ru.handh.school.igor.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.handh.school.igor.R

data class AppTextStyles(
    val normalText: TextStyle,
    val lightText: TextStyle,
    val smallText: TextStyle,
    val mediumText: TextStyle,
    val largeText: TextStyle,
    val highlightedText: TextStyle,
    val body: TextStyle,
    val subtitle: TextStyle,
    val caption: TextStyle,
    val heading1: TextStyle,
    val heading2: TextStyle,
    val heading3: TextStyle
)

val defaultTextStyles = AppTextStyles(
    normalText = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_500))
    ),
    lightText = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_300))
    ),
    smallText = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    ),
    mediumText = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    ),
    largeText = TextStyle(
        fontSize = 24.sp,
        lineHeight = 34.sp,
        fontFamily = FontFamily(Font(R.font.roboto_500))
    ),
    highlightedText = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    ),
    body = TextStyle(
        fontSize = 20.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    ),
    subtitle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    ),
    caption = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto_500))
    ),
    heading1 = TextStyle(
        fontSize = 32.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_500))
    ),
    heading2 = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    ),
    heading3 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_500))
    )
)
