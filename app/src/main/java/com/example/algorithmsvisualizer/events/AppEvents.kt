package com.example.algorithmsvisualizer.events

import com.example.algorithmsvisualizer.data.model.Algorithm

sealed class AppEvents {

    //AlgorithmGroupList Screen events
    class AlgorithmGroupClick(val algorithmList: List<Algorithm>) : AppEvents()

    //AlgorithmList Screen events
    class AlgorithmClick(val algorithm: Algorithm): AppEvents()

    //Algorithm Visualizer Screen events
    class SortAlgorithm(val algorithm: Algorithm, val arr:Array<Int>, val delay:Long): AppEvents()
    class DeleteItem(val index: Int) : AppEvents()
    class UpdateItem(val index: Int, val value: Int): AppEvents()
    object Pause: AppEvents()
    class IncreaseDelay(val increaseAmount: Long): AppEvents()
    class DecreaseDelay(val decreaseAmount: Long): AppEvents()
    object NextStep : AppEvents()
    object PreviousStep : AppEvents()
    class Initialization(val algorithm: Algorithm) : AppEvents()
}
