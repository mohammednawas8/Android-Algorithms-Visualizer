package com.example.algorithmsvisualizer.algorithms



abstract class SortingAlgorithms() {

    abstract suspend fun sort(
        arr: Array<Int>,
        iChange: (Int) -> Unit,
        jChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit
    )
}
