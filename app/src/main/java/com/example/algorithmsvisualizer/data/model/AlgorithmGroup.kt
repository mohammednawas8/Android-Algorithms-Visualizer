package com.example.algorithmsvisualizer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlgorithmGroup(
    @PrimaryKey(autoGenerate = true)
    val groupId: Int = 0,
    val name: String,
    val count: Int
)