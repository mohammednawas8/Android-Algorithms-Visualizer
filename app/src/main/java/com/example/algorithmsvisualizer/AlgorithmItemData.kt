package com.example.algorithmsvisualizer

import androidx.compose.ui.graphics.painter.Painter
import com.example.algorithmsvisualizer.data.model.Algorithm

data class AlgorithmItemData(
    val image: Painter,
    val algorithm: Algorithm
) {
}