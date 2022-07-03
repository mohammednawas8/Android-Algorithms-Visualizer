package com.example.algorithmsvisualizer.algorithms

class MergeSort : SortingAlgorithms() {

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]

    override suspend fun sort(
        arr: Array<Int>,
        iChange: (Int) -> Unit,
        jChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit,
    ) {
        sort(arr, 0, arr.size - 1, onSwap = onSwap)
    }

    suspend fun sort(
        arr: Array<Int>,
        l: Int,
        r: Int,
        onSwap: (Array<Int>) -> Unit,
    ) {
        if (l < r) {
            val mid = l + (r - l) / 2
            sort(arr, l, mid, onSwap = { onSwap(arr) })
            sort(arr, mid + 1, r, onSwap = { onSwap(arr) })
            merge(arr, l, mid, r, onSwap = { onSwap(arr) })
        }
    }


    private suspend fun merge(
        arr: Array<Int>,
        l: Int, m: Int,
        r: Int,
        onSwap: () -> Unit,
    ) {
        // Find sizes of two subarrays to be merged
        val n1 = m - l + 1
        val n2 = r - m

        /* Create temp arrays */
        val L = Array<Int>(n1) { 0 }
        val R = Array<Int>(n2) { 0 }


        /**
         * Copy data to temp arrays
         */

        for (i in 0 until n1)
            L[i] = arr[l + i]
        for (i in 0 until n2)
            R[i] = arr[m + 1 + i]

        /**
         * Merge the temp arrays
         * Initial indexes of first and second subarrays
         */
        var i = 0
        var j = 0

        // Initial index of merged subarray array
        var k = l
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i]
                onSwap()
                i++
            } else {
                arr[k] = R[j]
                onSwap()
                j++
            }
            k++
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i]
            onSwap()
            i++
            k++
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j]
            onSwap()
            j++
            k++
        }
    }
}

