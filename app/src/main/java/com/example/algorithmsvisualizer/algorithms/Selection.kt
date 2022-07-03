package com.example.algorithmsvisualizer.algorithms

class Selection {

    fun sort(arr: Array<Int>) {
        val n = arr.size

        // One by one move boundary of unsorted subarray
        for (i in 0 until n - 1) {
            // Find the minimum element in unsorted array
            var min_idx = i
            for (j in i + 1 until n)
                if (arr[j] < arr[min_idx]) min_idx = j

            // Swap the found minimum element with the first
            // element
            val temp = arr[min_idx]
            arr[min_idx] = arr[i]
            arr[i] = temp
        }
    }
}