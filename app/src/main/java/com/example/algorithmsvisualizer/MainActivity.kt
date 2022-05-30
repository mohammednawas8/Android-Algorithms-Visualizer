package com.example.algorithmsvisualizer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.algorithmsvisualizer.data.db.AlgorithmDatabase
import com.example.algorithmsvisualizer.navigation.Navigation
import com.example.algorithmsvisualizer.ui.theme.AlgorithmsVisualizerTheme
import com.example.algorithmsvisualizer.viewmodel.AlgorithmViewModel
import dagger.hilt.android.AndroidEntryPoint

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

