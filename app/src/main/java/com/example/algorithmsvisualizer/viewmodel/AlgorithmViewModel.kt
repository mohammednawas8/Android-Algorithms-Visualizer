package com.example.algorithmsvisualizer.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.algorithms.sorting.InsertionSort
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.helper.ArrayOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlgorithmViewModel : ViewModel() {

    val onSortingFinish = mutableStateOf(false)

    val arrState = mutableStateOf(arrayOf(100, 120,80,55,90, 150, 170, 30, 20, 310, 17, 100, 67, 90))


    fun onAction(event: AppEvents) {
        when (event) {

            is AppEvents.AlgorithmSorting -> {

                onSortingFinish.value = false

                val algorithm = event.algorithm
                arrState.value = event.arr
                val delay = event.delay
                if (algorithm.name == "Insertion Sort") {
                    insertionSort(arrState.value, delay)
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
            delayDuration,
            jChange = {

            },
            iChange = {

            },
            onSwap = {
                val copy = Array<Int>(it.size,{1})
                it.forEachIndexed { i,index->
                    copy[i] = index
                }
                arrState.value = copy
            }
        )

        onSortingFinish.value = true

    }


    fun deleteItem(index: Int){
        ArrayOperations.deleteArrayElement(arrState.value, index)
    }

}