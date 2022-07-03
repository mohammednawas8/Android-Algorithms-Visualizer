package com.example.algorithmsvisualizer.algorithms

class SelectionSort: SortingAlgorithms() {
    override suspend fun sort(
        arr: Array<Int>,
        iChange: (Int) -> Unit,
        jChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit,
    ) {

            val n = arr.size

            // One by one move boundary of unsorted subarray
            for (i in 0 until n - 1) {
                // Find the minimum element in unsorted array
                var min_idx = i
                iChange(i)
                for (j in i + 1 until n) {
                    if (arr[j] < arr[min_idx]) min_idx = j
                    jChange(j)
                }

                // Swap the found minimum element with the first
                // element
                val temp = arr[min_idx]
                arr[min_idx] = arr[i]
                arr[i] = temp
                onSwap(arr)
            }
        }
    }
