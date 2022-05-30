package com.example.algorithmsvisualizer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    background = BlueGray900,
    surface = BlueGray850,
    onSurface = LightGray,
    secondary = Orange800,
    primary = Orange600
)

private val LightColorPalette = lightColors(
    background = BlueGray900,
    surface = BlueGray850,
    onSurface = LightGray,
    secondary = Orange800,
    primary = Orange600
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