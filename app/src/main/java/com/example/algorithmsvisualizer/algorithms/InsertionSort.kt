package com.example.algorithmsvisualizer.algorithms

class InsertionSort: SortingAlgorithms() {

    override suspend fun sort(
        arr: Array<Int>,
        iChange: (Int) -> Unit,
        jChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit,
    ) {
        for (i in 1 until arr.size) {
            iChange(i)
            var j = i - 1
            val key = arr[i]
            while (j >= 0 && key < arr[j]) {
                jChange(i)
                arr[j + 1] = arr[j]
                onSwap(arr)
                j -= 1
            }
            arr[j + 1] = key
            onSwap(arr)
        }
    }

}