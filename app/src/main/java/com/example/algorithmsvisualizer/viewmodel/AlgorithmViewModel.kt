package com.example.algorithmsvisualizer.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.algorithms.sorting.InsertionSort
import com.example.algorithmsvisualizer.events.AppEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlgorithmViewModel : ViewModel() {

    val onSortingFinish = mutableStateOf(false)

    fun onAction(event: AppEvents) {
        when (event) {

            is AppEvents.AlgorithmSorting -> {

                val algorithm = event.algorithm
                val arr = event.arr
                val delay = event.delay
                if (algorithm.name == "Insertion Sort") {
                    insertionSort(arr, delay)
                }

            }
            else -> {}
        }
    }

    private fun insertionSort(
        arr: Array<Int>,
        delayDuration: Long,

        ) = viewModelScope.launch {

        InsertionSort.sort(
            arr,
            delayDuration
        )

        onSortingFinish.value = true

    }

}