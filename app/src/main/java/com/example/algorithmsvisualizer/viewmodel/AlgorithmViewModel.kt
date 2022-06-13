package com.example.algorithmsvisualizer.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.algorithms.AlgorithmsImpl
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.helper.ArrayOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlgorithmViewModel @Inject constructor(
    private val algorithmsImpl: AlgorithmsImpl
) : ViewModel() {

    val onSortingFinish = mutableStateOf(false)

    val arrState =
        mutableStateOf(arrayOf(100, 120, 80, 55))



    fun onAction(event: AppEvents) {
        when (event) {

            is AppEvents.SortAlgorithm -> {
                onSortingFinish.value = false
                val algorithm = event.algorithm
                arrState.value = event.arr
                val delay = event.delay

                if (algorithm.name == "Insertion Sort") {
                    insertionSort(arrState.value, delay)
                }

            }

            is AppEvents.DeleteItem -> deleteItemFromArray(event.index)

            is AppEvents.Pause -> pauseInsertionSort()

            is AppEvents.IncreaseDelay -> algorithmsImpl.increaseDelay(event.increaseAmount)

            is AppEvents.DecreaseDelay -> algorithmsImpl.decreaseDelay(event.decreaseAmount)

            else -> {}
        }
    }

    private fun pauseInsertionSort() {
        algorithmsImpl.pause()
    }


    private fun insertionSort(
        arr: Array<Int>,
        delayDuration: Long,

        ) = viewModelScope.launch {
        algorithmsImpl.insertionSort(
            arr,
            delayDuration,
            jChange = {

            },
            iChange = {
                // Clear the array
                //add the new element

            },
            onSwap = {
                copyArrayIntoArrState(it, it.size)
            },
            onPause = {
                copyArrayIntoArrState(it, it.size)
                Log.d("test", it.toMutableList().toString())
            }
        )

        // Sorting is finished
        onSortingFinish.value = true

    }

    private fun copyArrayIntoArrState(arr: Array<Int>, size: Int) {
        val copy = Array(size) { 0 }
        arr.forEachIndexed { i, index ->
            copy[i] = index
        }
        arrState.value = copy
    }


    private fun deleteItemFromArray(index: Int) {
        val arrWithTheDeletedElement = ArrayOperations.deleteArrayElement(arrState.value, index)
        copyArrayIntoArrState(arrWithTheDeletedElement, arrWithTheDeletedElement.size)
    }

}