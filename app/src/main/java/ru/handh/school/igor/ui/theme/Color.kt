package ru.handh.school.igor.ui.theme

import androidx.compose.ui.graphics.Color

data class AppColors(

    val primaryBrand: Color,
    val primary: Color,
    val textOnControl: Color,
    val primaryDisabled: Color,
    val grey: Color
)

val defaultColors = AppColors(
    primaryBrand = Color(0xFF000000),
    primary = Color(0xFF0D6EFD),
    textOnControl = Color(0xFFFFFFFF),
    primaryDisabled = Color(0xFF62a0fd),
    grey = Color(0xFFC2C2C2)
)
