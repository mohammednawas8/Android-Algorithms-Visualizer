package com.example.algorithmsvisualizer.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.algorithms.AlgorithmsImpl
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.helper.ArrayOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlgorithmViewModel @Inject constructor(
    private val algorithmsImpl: AlgorithmsImpl,
) : ViewModel() {

    val onSortingFinish = mutableStateOf(false)

    val arrState =
        mutableStateOf(arrayOf(100, 120, 80, 55, 40, 5, 25, 320, 80, 23, 534, 64))

    var nextStep = 1
    var previousStep = 0

    @SuppressLint("MutableCollectionMutableState")
    var sortedArrLevels = mutableListOf<List<Int>>()

    private var stepSize = 2

    var delayDuration = 850L

    fun onAction(event: AppEvents) {
        when (event) {

            is AppEvents.SortAlgorithm -> {
                onSortingFinish.value = false
                val algorithm = event.algorithm
                arrState.value = event.arr
//                tempState = arrState.value.clone()
                val delay = event.delay
                this.delayDuration = delay
                startAlgorithm(algorithm.name, arrState.value.size)

            }

            is AppEvents.Initialization -> {
//                tempState = arrState.value.clone()
                insertionSort(arrState.value.clone())
            }

            is AppEvents.DeleteItem -> deleteItemFromArray(event.index)

            is AppEvents.UpdateItem -> updateItemInTheArray(event.index, event.value)

            is AppEvents.Pause -> pauseInsertionSort()

            is AppEvents.IncreaseDelay -> increaseDelay(event.increaseAmount)

            is AppEvents.DecreaseDelay -> decreaseDelay(event.decreaseAmount)

            is AppEvents.NextStep -> nextStep()

            is AppEvents.PreviousStep -> previousStep()

            else -> {}
        }
    }


    private fun decreaseDelay(decreaseAmount: Long) {
        if (delayDuration > 160) {
            delayDuration -= decreaseAmount
        }
    }

    private fun increaseDelay(increaseAmount: Long) {
        delayDuration += increaseAmount

    }

    private fun nextStep() {
        if (nextStep < sortedArrLevels.size && nextStep >= 1) {
            copyArrayIntoArrState(sortedArrLevels[nextStep].toIntArray(), arrState.value.size)
            Log.d("test","$nextStep $previousStep")
            nextStep++
            previousStep++
        }
    }

    private fun previousStep() {

        if (previousStep > 1) {
            copyArrayIntoArrState(sortedArrLevels[previousStep].toIntArray(), arrState.value.size)
            Log.d("test","$nextStep $previousStep")
            nextStep--
            previousStep--
        }

    }

    private fun startAlgorithm(name: String, size: Int) {
        when (name) {
            "Insertion Sort" -> viewModelScope.launch {
                sortedArrLevels.forEach {
                    delay(delayDuration)
                    copyArrayIntoArrState(it.toIntArray(), size)
                    Log.d("test", delayDuration.toString())
                }
                onSortingFinish.value = true
            }
        }

    }


    private fun pauseInsertionSort() {
        algorithmsImpl.pause()
    }


    private fun insertionSort(
        arr: Array<Int>,
    ) = viewModelScope.launch {
        algorithmsImpl.insertionSort(
            arr,
            iChange = { i ->

            },
            jChange = { j ->

            },
            onSwap = { arr ->
                addArrayIntoSortedArrLevels(arr)
            }
        )

        // Sorting is finished
        onSortingFinish.value = true

    }

    private fun copyArrayIntoArrState(arr: IntArray, size: Int) {
        val copy = Array(size) { 0 }
        arr.forEachIndexed { i, index ->
            copy[i] = index
        }
        arrState.value = copy
    }

    private fun addArrayIntoSortedArrLevels(arr: Array<Int>) {
        sortedArrLevels.add(arr.toList())
    }


    private fun deleteItemFromArray(index: Int) {
        //Delete the element and copy the new array to the arrState array
        val arrWithTheDeletedElement = ArrayOperations.deleteArrayElement(arrState.value, index)
        copyArrayIntoArrState(arrWithTheDeletedElement.toIntArray(), arrWithTheDeletedElement.size)
        refactorSortedArrayLevels()
    }

    private fun updateItemInTheArray(index: Int, value: Int) {
        Log.d("test", "updating")
        val tempArr = arrState.value.clone()
        tempArr[index] = value

        copyArrayIntoArrState(tempArr.toIntArray(), tempArr.size)
        refactorSortedArrayLevels()
    }

    private fun refactorSortedArrayLevels() {
        //Restore the sortedArrayLevels because we have already changed an element
        //and we resort the new arrState array
        sortedArrLevels = ArrayList()
        insertionSort(arrState.value.clone())

        //We don't pass the original arrState instead we pass a clone of it
    }

}