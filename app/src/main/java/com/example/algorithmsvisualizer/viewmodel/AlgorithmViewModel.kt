package com.example.algorithmsvisualizer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.algorithmsvisualizer.repo.AlgorithmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlgorithmViewModel @Inject constructor(
    algorithmRepository: AlgorithmRepository
): ViewModel() {

    val algorithmGroupWithAlgorithms = algorithmRepository.getAlgorithmGroupWithAlgorithms()




}