package com.example.algorithmsvisualizer.algorithms


class QuickSort {

    /**
     * The main function that implements QuickSort
     * arr[] --> Array to be sorted,
     * low --> Starting index,
     * high --> Ending index
     */
    suspend fun sort(arr: Array<Int>, low: Int, high: Int) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            val pi: Int = partition(arr, low, high)

            // Separately sort elements before
            // partition and after partition
            sort(arr, low, pi - 1)
            sort(arr, pi + 1, high)
        }
    }

    suspend fun partition(arr: Array<Int>, low: Int, high: Int): Int {

        // pivot
        val pivot = arr[high]

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        var i = low - 1
        for (j in low until high) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++
                swap(arr, i, j)
            }
        }
        swap(arr, i + 1, high)
        return i + 1
    }

    // A utility function to swap two elements
    suspend fun swap(arr: Array<Int>, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }

}