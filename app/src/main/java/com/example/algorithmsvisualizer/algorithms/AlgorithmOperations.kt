package com.example.algorithmsvisualizer.algorithms

abstract class AlgorithmOperations{

    private var delayDuration: Long = 300

    fun getDelay(): Long{
        return delayDuration
    }

    abstract fun increaseDelay(delay: Long)

    abstract fun decreaseDelay()

}