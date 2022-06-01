package com.example.algorithmsvisualizer.navigation

sealed class NavigationRout(val rout: String) {

    object AlgorithmListScreen: NavigationRout("als")
    object AlgorithmGroupListScreen: NavigationRout("agls")
    object AlgorithmVisualizerScreen: NavigationRout("alvls")
}
