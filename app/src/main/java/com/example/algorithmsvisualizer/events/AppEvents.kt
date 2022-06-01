package com.example.algorithmsvisualizer.events

import com.example.algorithmsvisualizer.data.model.Algorithm

sealed class AppEvents {
    class AlgorithmGroupClick(val algorithmList: List<Algorithm>) : AppEvents()
    class AlgorithmClick(val algorithm: Algorithm): AppEvents()
}
