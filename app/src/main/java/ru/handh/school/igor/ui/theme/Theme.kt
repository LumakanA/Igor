package ru.handh.school.igor.ui.theme

import android.app.Activity
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LocalOffsets = staticCompositionLocalOf { defaultOffsets }

private val LocalRoundings = staticCompositionLocalOf { defaultRoundings }

private val LocalColors = staticCompositionLocalOf { defaultColors }

private val LocalTextStyles = staticCompositionLocalOf { defaultTextStyles }

object AppTheme {
    val offsets: AppOffsets
        @Composable
        get() = LocalOffsets.current

    val roundings: AppRoundings
        @Composable
        get() = LocalRoundings.current

    val colors: AppColors
        @Composable
        get() = LocalColors.current

    val textStyles: AppTextStyles
        @Composable
        get() = LocalTextStyles.current
}

private object OverrideRippleTheme : RippleTheme {

    private val overrideRippleColor = Color(0xFFC1C1C1)

    @Composable
    override fun defaultColor(): Color = overrideRippleColor

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = overrideRippleColor,
        lightTheme = true
    )
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        CompositionWrapper(
            offsets = defaultOffsets,
            roundings = defaultRoundings,
            colors = defaultColors,
            textStyles = defaultTextStyles,
            rippleTheme = OverrideRippleTheme,
            content = content
        )
    }
}

@Composable
private fun CompositionWrapper(
    offsets: AppOffsets,
    roundings: AppRoundings,
    colors: AppColors,
    textStyles: AppTextStyles,
    rippleTheme: RippleTheme,
    content: @Composable () -> Unit
) {
    val offsetsCalculation = remember { offsets }
    val roundingsCalculation = remember { roundings }
    val colorsCalculation = remember { colors }
    val textStylesCalculation = remember { textStyles }
    val rippleThemeCalculation = remember { rippleTheme }
    CompositionLocalProvider(
        LocalOffsets provides offsetsCalculation,
        LocalRoundings provides roundingsCalculation,
        LocalColors provides colorsCalculation,
        LocalTextStyles provides textStylesCalculation,
        LocalRippleTheme provides rippleThemeCalculation,
        content = content
    )
}
