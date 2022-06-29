package com.example.algorithmsvisualizer.algorithms

import android.util.Log
import kotlinx.coroutines.delay

class AlgorithmsImpl() : Algorithms {

    private var delayDuration: Long? = null
    private var isPaused = false
    var size: Int = 0

    fun pause() {
        isPaused = true
    }

    fun increaseDelay(increaseAmount: Long) {
        if (delayDuration != null)
            delayDuration = increaseAmount + delayDuration!!

    }

    fun decreaseDelay(decreaseAmount: Long) {
        if (delayDuration != null) {
            if (delayDuration!! > 50)
                delayDuration = delayDuration!! - decreaseAmount
        }
    }


    override suspend fun insertionSort(
        arr: Array<Int>,
        jChange: (Int) -> Unit,
        iChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit,
    ) {
        for (j in 1 until arr.size) {
            jChange(j)
            var i = j - 1
            val key = arr[j]
            while (i >= 0 && key < arr[i]) {
                iChange(i)
                arr[i + 1] = arr[i]
                onSwap(arr)
                i -= 1
            }
            arr[i + 1] = key
            onSwap(arr)
        }
    }
}
