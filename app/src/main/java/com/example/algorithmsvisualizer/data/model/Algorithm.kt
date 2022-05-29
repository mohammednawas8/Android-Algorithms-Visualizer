package com.example.algorithmsvisualizer.data.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Algorithm(
    @PrimaryKey(autoGenerate = true)
    val algorithmId: Int,
    val name: String,
    val timeComplexity: String,
    val generalInformation: String,
    val groupId: Int
) {
}