package com.example.algorithmsvisualizer.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.repo.AlgorithmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlgorithmViewModel @Inject constructor(
    algorithmRepository: AlgorithmRepository,
) : ViewModel() {

    val algorithmGroupWithAlgorithms = algorithmRepository.getAlgorithmGroupWithAlgorithms()


    var algorithmListState = mutableStateOf(emptyList<Algorithm>())
        private set

    val algorithmState = mutableStateOf<Algorithm>(Algorithm(
        0, "", "", "", 0
    ))

    fun onAlgorithmGroupScreenAction(event: AppEvents) {
        when (event) {
            is AppEvents.AlgorithmGroupClick -> getAlgorithmList(event.algorithmList)
        }
    }

    fun onAlgorithmScreenAction(event: AppEvents) {
        when (event) {
            is AppEvents.AlgorithmClick -> getAlgorithm(event.algorithm)
        }
    }

    private fun getAlgorithm(algorithm: Algorithm) {
        algorithmState.value = algorithm

    }


    private fun getAlgorithmList(algorithmList: List<Algorithm>) {
        algorithmListState.value = algorithmList


    }
}