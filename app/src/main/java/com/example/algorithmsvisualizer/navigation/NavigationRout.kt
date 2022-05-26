package com.example.algorithmsvisualizer.navigation

sealed class NavigationRout(val rout: String) {

    object AlgorithmVisualizerScreenRout: NavigationRout("avls")
    object AlgorithmListScreenRout: NavigationRout("alls")
}
