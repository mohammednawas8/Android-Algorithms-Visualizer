package com.example.algorithmsvisualizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.algorithmsvisualizer.navigation.Navigation
import com.example.algorithmsvisualizer.ui.theme.AlgorithmsVisualizerTheme
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

