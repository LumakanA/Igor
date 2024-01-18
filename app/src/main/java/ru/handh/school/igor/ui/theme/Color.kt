package ru.handh.school.igor.ui.theme

import androidx.compose.ui.graphics.Color

data class AppColors(

    val primaryBrand: Color,
    val primary: Color,
    val surfaceBright: Color,
    val primaryDisabled: Color,
    val lightGrey: Color,
    val grey: Color,
    val red: Color,
    val yellow: Color,
    val green: Color,
    val surface: Color,
    val lightSurface: Color,
    val darkSurface: Color
)

val defaultColors = AppColors(
    primaryBrand = Color(0xFF000000),
    primary = Color(0xFF0D6EFD),
    surfaceBright = Color(0xFFFFFFFF),
    primaryDisabled = Color(0xFF62a0fd),
    grey = Color(0xFFC2C2C2),
    lightGrey = Color(0xFF49454F),
    red = Color(0xFFDC3545),
    yellow = Color(0xFFDCD535),
    green = Color(0xFF60DC35),
    surface = Color(0xFFFEF7FF),
    lightSurface = Color(0xFFEADDFF),
    darkSurface = Color(0xFF21005D)
)
