package com.example.algorithmsvisualizer.repo

import com.example.algorithmsvisualizer.data.db.AlgorithmDatabase
import com.example.algorithmsvisualizer.data.model.AlgorithmCode
import kotlinx.coroutines.flow.Flow

class AlgorithmRepositoryImpl(
    private val database: AlgorithmDatabase
) : AlgorithmRepository {

    private val dao = database.algorithmDao

    @Override
    override fun getAlgorithmGroupWithAlgorithms() = dao.getAlgorithmGroupWithAlgorithms()

    @Override
    override fun getAlgorithmWithAlgorithmCodes() = dao.getAlgorithmWithAlgorithmCodes()

    override fun getAlgorithmCodesByAlgorithmId(algorithmId: Int) =
        database.algorithmDao.getAlgorithmCodesByAlgorithmId(algorithmId)
}