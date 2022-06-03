package com.example.algorithmsvisualizer.events

import com.example.algorithmsvisualizer.data.model.Algorithm

sealed class AppEvents {
    class AlgorithmGroupClick(val algorithmList: List<Algorithm>) : AppEvents()
    class AlgorithmClick(val algorithm: Algorithm): AppEvents()
    class AlgorithmSorting(val algorithm: Algorithm,val arr:Array<Int>,val delay:Long): AppEvents()
}
