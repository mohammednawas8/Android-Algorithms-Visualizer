package com.example.algorithmsvisualizer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["code","algorithmId","programmingLanguage"])
data class AlgorithmCode(
    val code: String,
    val algorithmId: Int,
    val programmingLanguage: String
)
