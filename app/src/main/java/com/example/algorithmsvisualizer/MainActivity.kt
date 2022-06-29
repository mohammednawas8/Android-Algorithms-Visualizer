package com.example.algorithmsvisualizer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.algorithmsvisualizer.algorithms.AlgorithmsImpl
import com.example.algorithmsvisualizer.algorithms.MergeSort
import com.example.algorithmsvisualizer.algorithms.QuickSort
import com.example.algorithmsvisualizer.navigation.Navigation
import com.example.algorithmsvisualizer.ui.theme.AlgorithmsVisualizerTheme
import com.example.algorithmsvisualizer.viewmodel.AlgorithmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AlgorithmsVisualizerTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    Navigation(navController = navController,
                        modifier = Modifier
                            .fillMaxSize())
                }
            }
        }
    }
}

