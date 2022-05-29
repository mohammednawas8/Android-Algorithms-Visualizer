package com.example.algorithmsvisualizer.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.data.model.AlgorithmGroup

data class AlgorithmGroupWithAlgorithms(
    @Embedded val algorithmGroup: AlgorithmGroup,
    @Relation(
        parentColumn = "groupId",
        entityColumn = "groupId"
    )
    val algorithms: List<Algorithm>
) {
}