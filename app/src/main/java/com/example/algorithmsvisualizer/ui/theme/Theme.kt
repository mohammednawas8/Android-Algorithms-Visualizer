package com.example.algorithmsvisualizer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    background = BlueGray900,
    surface = BlueGray850,
    onSurface = LightGrayBlue,
    secondary = Orange800,
    primary = Orange600,
    secondaryVariant = GrayBlue

)

private val LightColorPalette = lightColors(
    background = BlueGray900,
    surface = BlueGray850,
    onSurface = LightGrayBlue,
    secondary = Orange800,
    primary = Orange600,
    secondaryVariant = GrayBlue
)

@Composable
fun AlgorithmsVisualizerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}