package com.example.algorithmsvisualizer.algorithms

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

class AlgorithmsImpl(): Algorithms {

    private var delayDuration: Long? = null
    private var isPaused = false
    private var jState = 1


    override suspend fun insertionSort(
        arr: Array<Int>,
        delay: Long,
        jChange: (Int) -> Unit,
        iChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit,
        onPause: (Array<Int>) -> Unit
    ) {

        delayDuration = delay
        isPaused = false
        val size = arr.size
        for (j in jState until size) {

            jChange(j)
            jState = j

            if (isPaused) {
                onPause(arr)
                return
            }

            val key = arr[j]
            var i = j - 1

            while (i >= 0 && arr[i] > key) {

                if (isPaused) {
                    onPause(arr)

                    return
                }

                iChange(i)
                delay(delayDuration!!)
                arr[i + 1] = arr[i]
                onSwap(arr)
                i -= 1
            }
            delay((delayDuration!! * 1.5).toLong())
            arr[i + 1] = key
            onSwap(arr)
        }

        isPaused = false

    }




    fun pause() {
        isPaused = true
    }


    fun resume() {

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

}

