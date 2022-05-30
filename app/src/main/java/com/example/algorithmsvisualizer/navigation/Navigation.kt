package com.example.algorithmsvisualizer.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.algorithmsvisualizer.AlgorithmVisualizeScreen
import com.example.algorithmsvisualizer.AlgorithmsListScreen
import com.example.algorithmsvisualizer.events.AlgorithmScreenListEvents
import com.example.algorithmsvisualizer.viewmodel.AlgorithmViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: AlgorithmViewModel = hiltViewModel(),
) {

    NavHost(
        navController = navController,
        startDestination = NavigationRout.AlgorithmListScreenRout.rout,
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .then(modifier)
    ) {

        composable(route = NavigationRout.AlgorithmListScreenRout.rout) {
            AlgorithmsListScreen { algorithmGroupId,algorithmList ->
                viewModel.onAlgorithmListScreenAction(AlgorithmScreenListEvents.AlgorithmGroupClick(
                    algorithmList))
                navController.navigate(NavigationRout.AlgorithmVisualizerScreenRout.rout + "/$algorithmGroupId")
            }
        }

        composable(
            route = NavigationRout.AlgorithmVisualizerScreenRout.rout + "/{groupId}",
            arguments = listOf(
                navArgument("groupId") {
                    nullable = false
                    type = NavType.IntType
                }
            )) {
            val groupId = remember {
                it.arguments?.getInt("groupId")
            }

            AlgorithmVisualizeScreen(
                navController = navController,
                groupId!!
            )
        }

    }

}