package com.example.algorithmsvisualizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.algorithmsvisualizer.ui.theme.AlgorithmsVisualizerTheme

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

