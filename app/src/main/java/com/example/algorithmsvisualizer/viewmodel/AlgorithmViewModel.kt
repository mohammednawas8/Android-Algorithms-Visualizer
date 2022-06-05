package com.example.algorithmsvisualizer.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.algorithms.sorting.InsertionSort
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.helper.ArrayOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlgorithmViewModel @Inject constructor(
    private val insertionSort: InsertionSort
) : ViewModel() {

    val onSortingFinish = mutableStateOf(false)

    val arrState =
        mutableStateOf(arrayOf(100, 120, 80, 55, 90, 150, 170, 30, 20, 310, 17, 100, 67, 90))


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

            is AppEvents.IncreaseDelay -> insertionSort.increaseDelay(event.increaseAmount)

            is AppEvents.DecreaseDelay -> insertionSort.decreaseDelay(event.decreaseAmount)

            else -> {}
        }
    }

    private fun pauseInsertionSort() {
        insertionSort.pause()
    }



    private fun insertionSort(
        arr: Array<Int>,
        delayDuration: Long,

        ) = viewModelScope.launch {
        insertionSort.sort(
            arr,
            delayDuration,
            jChange = {

            },
            iChange = {

            },
            onSwap = {
                copyArrayIntoArrState(it, it.size)
            },
            onPause = {
                copyArrayIntoArrState(it,it.size)
                Log.d("test",it.toMutableList().toString())
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