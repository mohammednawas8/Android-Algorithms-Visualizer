package com.example.algorithmsvisualizer

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.algorithmsvisualizer.navigation.NavigationRout

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = NavigationRout.AlgorithmVisualizerScreenRout.rout,
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .then(modifier)
    ) {

        composable(route = NavigationRout.AlgorithmVisualizerScreenRout.rout) {
            AlgorithmsListScreen(navController = navController)
        }

        composable(route = NavigationRout.AlgorithmListScreenRout.rout) {
            AlgorithmVisualizeScreen(navController = navController)
        }

    }

}