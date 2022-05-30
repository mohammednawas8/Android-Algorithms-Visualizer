package com.example.algorithmsvisualizer.events

import com.example.algorithmsvisualizer.data.model.Algorithm

sealed class AlgorithmScreenListEvents {
    class AlgorithmGroupClick(val algorithmList: List<Algorithm>) : AlgorithmScreenListEvents()
}
