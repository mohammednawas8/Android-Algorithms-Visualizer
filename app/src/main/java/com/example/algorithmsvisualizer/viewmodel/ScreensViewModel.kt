package com.example.algorithmsvisualizer.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algorithmsvisualizer.data.db.relations.AlgorithmWithCodes
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.data.model.AlgorithmCode
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.repo.AlgorithmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreensViewModel @Inject constructor(
    private val algorithmRepository: AlgorithmRepository,
) : ViewModel() {

    val algorithmGroupWithAlgorithms = algorithmRepository.getAlgorithmGroupWithAlgorithms()


    var algorithmListState = mutableStateOf(emptyList<Algorithm>())
        private set

    val algorithmState = mutableStateOf<Algorithm>(
        Algorithm(
            0, "", "", "", 0
        )
    )

    var algorithmCodes = mutableStateOf<List<AlgorithmCode>>(emptyList())
        private set

    fun onAlgorithmGroupScreenAction(event: AppEvents) {
        when (event) {
            is AppEvents.AlgorithmGroupClick -> getAlgorithmList(event.algorithmList)
        }
    }

    fun onAlgorithmScreenAction(event: AppEvents) {
        when (event) {
            is AppEvents.AlgorithmClick -> {
                getAlgorithm(event.algorithm)
                getAlgorithmCodes(event.algorithm)
            }
        }
    }

    private fun getAlgorithmCodes(algorithm: Algorithm) = viewModelScope.launch {
        algorithmRepository.getAlgorithmCodesByAlgorithmId(algorithm.algorithmId).collect {
            algorithmCodes.value = it
        }
    }

    private fun getAlgorithm(algorithm: Algorithm) {
        algorithmState.value = algorithm

    }


    private fun getAlgorithmList(algorithmList: List<Algorithm>) {
        algorithmListState.value = algorithmList
    }
}