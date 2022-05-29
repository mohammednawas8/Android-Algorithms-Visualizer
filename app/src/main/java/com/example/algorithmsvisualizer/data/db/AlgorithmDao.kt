package com.example.algorithmsvisualizer.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.algorithmsvisualizer.data.db.relations.AlgorithmGroupWithAlgorithms
import com.example.algorithmsvisualizer.data.db.relations.AlgorithmWithCodes
import com.example.algorithmsvisualizer.data.model.Algorithm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlgorithmDao {

    @Transaction
    @Query("SELECT * FROM AlgorithmGroup")
    fun getAlgorithmGroupWithAlgorithms(): Flow<List<AlgorithmGroupWithAlgorithms>>

    @Transaction
    @Query("SELECT * FROM Algorithm WHERE Algorithm.groupId = :groupId")
    fun getAlgorithmsByGroupId(groupId: Int): Flow<List<Algorithm>>

    @Transaction
    @Query("SELECT * FROM Algorithm")
    fun getAlgorithmWithAlgorithmCodes(): Flow<List<AlgorithmWithCodes>>

}