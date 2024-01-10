package ru.handh.school.igor.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.handh.school.igor.R

data class AppTextStyles(
    val buttonText: TextStyle,
    val text2: TextStyle,
    val text3: TextStyle,
    val text4: TextStyle,
    val text5: TextStyle,
    val text6: TextStyle,
    val text7: TextStyle,
    val text8: TextStyle,
    val text9: TextStyle
)

val defaultTextStyles = AppTextStyles(
    buttonText = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_500))
    ),
    text2 = TextStyle(
        fontSize = 32.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_500))
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
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    ),
    text7 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    text8 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 34.sp
    ),
    text9 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp
    )
)
