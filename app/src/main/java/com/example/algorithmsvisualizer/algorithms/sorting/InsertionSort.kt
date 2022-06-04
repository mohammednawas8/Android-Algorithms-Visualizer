package com.example.algorithmsvisualizer.algorithms.sorting

import android.util.Log
import kotlinx.coroutines.delay

class InsertionSort {

    // { 10 , 18 , 4 , 9 , 5 , 2 }
    companion object {
        suspend fun sort(
            arr: Array<Int>,
            delayDuration: Long,
            jChange: (Int) -> Unit,
            iChange: (Int) -> Unit,
            onSwap: (Array<Int>) -> Unit
        ) {
            val size = arr.size
            for (j in 1 until size) {
                jChange(j)
                val key = arr[j]
                var i = j - 1
                while (i >= 0 && arr[i] > key) {
                    iChange(i)
                    delay(delayDuration)
                    arr[i + 1] = arr[i]
                    onSwap(arr)
                    i -= 1

                }
                delay(delayDuration * 3)
                arr[i + 1] = key

                onSwap(arr)
            }
        }
    }

}