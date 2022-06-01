package com.example.algorithmsvisualizer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.viewmodel.AlgorithmViewModel

@Composable
fun AlgorithmVisualizerScreen(
    algorithmId: Int,
    viewModel: AlgorithmViewModel,
    navController: NavController,
) {
    val algorithm = viewModel.algorithmState.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(name = algorithm.name, timeComplexity = algorithm.timeComplexity) {
                navController.navigateUp()
            }
        },
        backgroundColor = MaterialTheme.colors.background
    ) {

    }

}


@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    name: String,
    timeComplexity: String,
    onBackClick: () -> Unit,
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(30.dp)
                )
            }

            Text(text = name,
                style = MaterialTheme.typography.h2,
                color = Color.White,
                fontSize = 18.sp)

            Text(text = timeComplexity,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 10.dp)
            )


        }
    }
}