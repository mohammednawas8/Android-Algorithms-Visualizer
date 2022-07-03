package com.example.algorithmsvisualizer.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.algorithms.*
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.helper.ArrayOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlgorithmViewModel @Inject constructor(
    private val insertionSort: InsertionSort,
    private val selectionSort: SelectionSort,
    private val bubbleSort: BubbleSort,
    private val mergeSort: MergeSort,
    private val quickSort: QuickSort,
    private val heapSort: HeapSort,
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
                isPaused = false
                onSortingFinish.value = false
                arrState.value = event.arr
                val delay = event.delay
                this.delayDuration = delay
                startAlgorithm(arrState.value.size)

            }

            is AppEvents.Initialization -> {
                prepareSortedArrLevels(event.algorithm)
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

    private fun prepareSortedArrLevels(algorithm: Algorithm) {
        when (algorithm.name) {
            "Insertion Sort" -> {
                insertionSort(arrState.value.clone())
            }
            "Selection Sort" -> {
                selectionSort(arrState.value.clone())
            }
            "Merge Sort" -> {
                mergeSort(arrState.value.clone())
            }
            "Heap Sort" -> {
                heapSort(arrState.value.clone())
            }
            "Quick Sprt" -> {
                quickSort(arrState.value.clone())
            }
            "Bubble Sort" -> {
                bubbleSort(arrState.value.clone())
            }
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

    private var isNextStepClicked = false
    private fun nextStep() {
        if (nextStep < sortedArrLevels.size && nextStep >= 0) {
            copyArrayIntoArrState(sortedArrLevels[nextStep].toIntArray(), arrState.value.size)
            sortingState = nextStep
            nextStep++
            previousStep++
            isNextStepClicked = true
        }
    }

    private fun previousStep() {
        //Solve bug
        //bug description : When the user clicks on nextStep the previousStep will increment by 1
        //the problem is when the user clicks on previous step after that increment.
        //the solution is to check if the next step already clicked. if so then we decrement previousStep by 1.
        if (isNextStepClicked) {
            previousStep--
            nextStep--
            isNextStepClicked = false
        }

        if (previousStep >= 0) {
            copyArrayIntoArrState(sortedArrLevels[previousStep].toIntArray(), arrState.value.size)
            //To prevent the previousStep becoming -1
            if (previousStep != 0) {
                sortingState = previousStep
                nextStep--
                previousStep--
            }
        }
    }

    private var isPaused = false
    private var sortingState = 0
    private fun startAlgorithm(size: Int) = viewModelScope.launch {
        for (i in sortingState until sortedArrLevels.size) {
            if (!isPaused) {
                delay(delayDuration)
                copyArrayIntoArrState(sortedArrLevels[i].toIntArray(), size)
            } else {
                sortingState = i
                nextStep = i + 1
                previousStep = i
                return@launch
            }
        }
        onSortingFinish.value = true
    }

    private fun pauseInsertionSort() {
        isPaused = true
    }


    private fun insertionSort(
        arr: Array<Int>,
    ) = viewModelScope.launch {
        insertionSort.sort(
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

    private fun selectionSort(
        arr: Array<Int>,
    ) = viewModelScope.launch {
        selectionSort.sort(
            arr,
            iChange = { i ->

            },
            jChange = { j ->

            },
            onSwap = { arr ->
                addArrayIntoSortedArrLevels(arr)
            }
        )
    }


    private fun mergeSort(
        arr: Array<Int>
    ) = viewModelScope.launch {
        mergeSort.sort(
            arr,
            iChange = { i ->

            },
            jChange = { j ->

            },
            onSwap = { arr ->
                addArrayIntoSortedArrLevels(arr)
            }
        )
    }

    private fun heapSort(
        arr: Array<Int>
    ) = viewModelScope.launch {
        heapSort.sort(
            arr,
            iChange = { i ->

            },
            jChange = { j ->

            },
            onSwap = { arr ->
                addArrayIntoSortedArrLevels(arr)
            }
        )
    }

    private fun quickSort(
        arr: Array<Int>
    ) = viewModelScope.launch {
        quickSort.sort(
            arr,
            iChange = { i ->

            },
            jChange = { j ->

            },
            onSwap = { arr ->
                addArrayIntoSortedArrLevels(arr)
            }
        )
    }

    private fun bubbleSort(
        arr: Array<Int>
    ) = viewModelScope.launch {
        bubbleSort.sort(
            arr,
            iChange = { i ->

            },
            jChange = { j ->

            },
            onSwap = { arr ->
                addArrayIntoSortedArrLevels(arr)
            }
        )
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