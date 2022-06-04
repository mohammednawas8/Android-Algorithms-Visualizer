package com.example.algorithmsvisualizer.algorithms.sorting

import android.util.Log
import kotlinx.coroutines.delay

class InsertionSort() {

    private var delayDuration: Long? = null
    private var isPaused = false
    private var jState = 1

    fun pause() {
        isPaused = true
    }

    suspend fun sort(
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
                iChange(i)
                delay(delayDuration!!)
                arr[i + 1] = arr[i]
                onSwap(arr)
                i -= 1
            }
            delay(delayDuration!! * 3)
            arr[i + 1] = key
            onSwap(arr)
        }

        isPaused = false
    }


    fun resume() {

    }

    fun increaseDelay() {
        if (delayDuration != null)
            delayDuration = (delayDuration!! / 2) + delayDuration!!

    }

    fun decreaseDelay() {
        if (delayDuration != null) {
            if (delayDuration!! > 50)
                delayDuration = delayDuration!! - (delayDuration!! / 2)
        }
    }
}

