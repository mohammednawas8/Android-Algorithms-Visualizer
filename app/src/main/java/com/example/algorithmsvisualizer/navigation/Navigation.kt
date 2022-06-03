package com.example.algorithmsvisualizer.navigation

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
import com.example.algorithmsvisualizer.AlgorithmGroupListScreen
import com.example.algorithmsvisualizer.AlgorithmListScreen
import com.example.algorithmsvisualizer.AlgorithmVisualizerScreen
import com.example.algorithmsvisualizer.events.AppEvents
import com.example.algorithmsvisualizer.viewmodel.AlgorithmViewModel
import com.example.algorithmsvisualizer.viewmodel.ScreensViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    screenViewModel: ScreensViewModel = hiltViewModel(),
    algorithmViewModel: AlgorithmViewModel = hiltViewModel(),
) {

    NavHost(
        navController = navController,
        startDestination = NavigationRout.AlgorithmGroupListScreen.rout,
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .then(modifier)
    ) {

        composable(route = NavigationRout.AlgorithmGroupListScreen.rout) {

            AlgorithmGroupListScreen(screenViewModel) { algorithmGroupId, algorithmList ->
                screenViewModel.onAlgorithmGroupScreenAction(AppEvents.AlgorithmGroupClick(
                    algorithmList))

                navController.navigate(NavigationRout.AlgorithmListScreen.rout + "/$algorithmGroupId")
            }
        }

        composable(
            route = NavigationRout.AlgorithmListScreen.rout + "/{groupId}",
            arguments = listOf(
                navArgument("groupId") {
                    nullable = false
                    type = NavType.IntType
                }
            )) {
            val groupId = remember {
                it.arguments?.getInt("groupId")
            }

            AlgorithmListScreen(
                navController = navController,
                groupId!!,
                screenViewModel
            )
        }

        composable(
            route = NavigationRout.AlgorithmVisualizerScreen.rout + "/{algorithmId}",
            arguments = listOf(
                navArgument("algorithmId") {
                    nullable = false
                    type = NavType.IntType
                }
            )
        ) {
            val algorithmId = remember {
                it.arguments?.getInt("algorithmId")
            }

            AlgorithmVisualizerScreen(algorithmId = algorithmId!!,
                screenViewModel = screenViewModel,
                algorithmViewModel = algorithmViewModel,
                navController = navController
            )

        }


    }

}