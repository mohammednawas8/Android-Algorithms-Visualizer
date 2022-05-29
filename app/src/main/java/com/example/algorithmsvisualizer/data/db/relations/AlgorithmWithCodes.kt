package com.example.algorithmsvisualizer.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.data.model.AlgorithmCode

data class AlgorithmWithCodes(
    @Embedded val algorithm: Algorithm,
    @Relation(
        parentColumn = "algorithmId",
        entityColumn = "algorithmId"
    )
    val codes: List<AlgorithmCode>
)
