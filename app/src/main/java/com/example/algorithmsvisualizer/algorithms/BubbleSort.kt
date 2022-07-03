package com.example.algorithmsvisualizer.algorithms

class BubbleSort: SortingAlgorithms() {

    override suspend fun sort(
        arr: Array<Int>,
        iChange: (Int) -> Unit,
        jChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit,
    ) {
        val n = arr.size
        for (i in 0 until n - 1){
            iChange(i)
            for (j in 0 until n - i - 1) {
                jChange(j)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                    onSwap(arr)
                }
            }
        }
    }
}