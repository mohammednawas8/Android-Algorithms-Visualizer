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


    private var jState = 1
    private var iState = -1
    private var key = -1
    private var i = 1

    override suspend fun insertionSort(
        arr: Array<Int>,
        delay: Long,
        jChange: (Int) -> Unit,
        iChange: (Int) -> Unit,
        onSwap: (Array<Int>) -> Unit,
        onPause: (Array<Int>) -> Unit,
        size: Int
    ) {

        delayDuration = delay
        isPaused = false
        for (j in jState until size) {

            jChange(j)
            jState = j

            if (isPaused) {
                onPause(arr)
                return

            }
            Log.d("test2",j.toString())

            if (iState != -1) {
                i = iState
                iState = -1
            } else {
                key = arr[j]
                i = j - 1
            }

            while (i >= 0 && arr[i] > key) {

                if (isPaused) {
                    onPause(arr)
                    iState = i + 1
                    jState = j - 1
                    Log.d("test", (i + 1).toString())
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

        // When the algorithm finish reset the jState and iState
        jState = 1
        iState = 0
        isPaused = false
    }





}
