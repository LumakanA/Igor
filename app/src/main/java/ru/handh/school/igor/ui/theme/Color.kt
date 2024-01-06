package ru.handh.school.igor.ui.theme

import androidx.compose.ui.graphics.Color

data class AppColors(

    val primaryBrand: Color,
    val primary: Color,
    val surfaceBright: Color,
    val primaryDisabled: Color,
    val grey: Color,
    val red: Color,
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
    red = Color(0xFFDC3545),
    surface = Color(0xFFFEF7FF),
    lightSurface = Color(0xFFEADDFF),
    darkSurface = Color(0xFF21005D)
)
