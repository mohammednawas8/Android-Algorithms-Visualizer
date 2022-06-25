package com.example.algorithmsvisualizer.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.algorithms.AlgorithmsImpl
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.helper.ArrayOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.multibindings.ElementsIntoSet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlgorithmViewModel @Inject constructor(
    private val algorithmsImpl: AlgorithmsImpl
) : ViewModel() {

    val onSortingFinish = mutableStateOf(false)

    val arrState =
        mutableStateOf(arrayOf(100, 120, 80, 55, 40, 5, 25, 320, 80,23,534,64))

    private var stepSize = 2

    fun onAction(event: AppEvents) {
        when (event) {

            is AppEvents.SortAlgorithm -> {
                onSortingFinish.value = false
                val algorithm = event.algorithm
                arrState.value = event.arr
                val delay = event.delay

                startAlgorithm(algorithm.name, delay, arrState.value.size)

            }

            is AppEvents.DeleteItem -> deleteItemFromArray(event.index)

            is AppEvents.Pause -> pauseInsertionSort()

            is AppEvents.IncreaseDelay -> algorithmsImpl.increaseDelay(event.increaseAmount)

            is AppEvents.DecreaseDelay -> algorithmsImpl.decreaseDelay(event.decreaseAmount)

            is AppEvents.NextStep -> nextStep(event.algorithm.name)


            else -> {}
        }
    }

    private fun nextStep(name: String) {

    }

    private fun startAlgorithm(name: String, delay: Long, size: Int) {
        when (name) {
            "Insertion Sort" -> insertionSort(arrState.value, delay, size)
        }


    }


    private fun pauseInsertionSort() {
        algorithmsImpl.pause()
    }


    private fun insertionSort(
        arr: Array<Int>,
        delayDuration: Long,
        size: Int
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
            },
            size
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