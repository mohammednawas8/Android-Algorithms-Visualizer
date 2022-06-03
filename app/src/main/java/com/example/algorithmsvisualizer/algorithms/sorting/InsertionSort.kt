package com.example.algorithmsvisualizer.algorithms.sorting

import android.util.Log
import kotlinx.coroutines.delay

class InsertionSort {

    // { 10 , 18 , 4 , 9 , 5 , 2 }
    companion object {
        suspend fun sort(
            arr: Array<Int>,
            delayDuration: Long
        ) {
            val size = arr.size
            for (j in 1 until size) {
//                delay(delayDuration)
                val key = arr[j]
                var i = j - 1
                while (i >= 0 && arr[i] > key) {
//                    delay(delayDuration)
                    arr[i + 1] = arr[i]
                    i -= 1
                }
                arr[i + 1] = key
            }
        }
    }

}