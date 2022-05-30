package com.example.algorithmsvisualizer.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.events.AlgorithmScreenListEvents
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

    fun onAlgorithmListScreenAction(event: AlgorithmScreenListEvents) {
        when (event) {
            is AlgorithmScreenListEvents.AlgorithmGroupClick -> getAlgorithmList(event.algorithmList)
        }
    }

    private fun getAlgorithmList(algorithmList: List<Algorithm>) {
        algorithmListState.value = algorithmList
    }
}