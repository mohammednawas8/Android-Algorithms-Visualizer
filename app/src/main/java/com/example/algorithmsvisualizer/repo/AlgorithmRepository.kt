package com.example.algorithmsvisualizer.repo

import com.example.algorithmsvisualizer.data.db.relations.AlgorithmGroupWithAlgorithms
import com.example.algorithmsvisualizer.data.db.relations.AlgorithmWithCodes
import com.example.algorithmsvisualizer.data.model.AlgorithmCode
import com.example.algorithmsvisualizer.util.Resource
import kotlinx.coroutines.flow.Flow

interface AlgorithmRepository {

     fun getAlgorithmGroupWithAlgorithms(): Flow<List<AlgorithmGroupWithAlgorithms>>

     fun getAlgorithmWithAlgorithmCodes(): Flow<List<AlgorithmWithCodes>>

     fun getAlgorithmCodesByAlgorithmId(algorithmId: Int): Flow<List<AlgorithmCode>>
}