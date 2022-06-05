package com.example.algorithmsvisualizer.algorithms

interface Algorithms {

    suspend fun insertionSort(
        arr: Array<Int>,
        delay: Long,
        jChange: (Int) -> Unit,
        iChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit,
        onPause: (Array<Int>) -> Unit
    )

}