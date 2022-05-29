package com.example.algorithmsvisualizer.repo

import com.example.algorithmsvisualizer.data.db.AlgorithmDatabase

class AlgorithmRepositoryImpl(
    private val database: AlgorithmDatabase
): AlgorithmRepository {

    private val dao = database.algorithmDao

    @Override
    override fun getAlgorithmGroupWithAlgorithms() = dao.getAlgorithmGroupWithAlgorithms()

    @Override
    override fun getAlgorithmWithAlgorithmCodes() = dao.getAlgorithmWithAlgorithmCodes()



}