package com.example.algorithmsvisualizer

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.viewmodel.AlgorithmViewModel

@Composable
fun AlgorithmVisualizeScreen(
    navController: NavController,
    groupId: Int,
    viewModel: AlgorithmViewModel = hiltViewModel(),
) {

    val algorithmList = viewModel.algorithmListState

    Log.d("Test",viewModel.algorithmListState.value.toString())

    Column(modifier = Modifier.fillMaxSize()) {
        AlgorithmList(algorithmsItems = algorithmList.value, onClick = {

        })

    }

}


@Composable
fun AlgorithmList(
    algorithmsItems: List<Algorithm>,
    onClick: (Algorithm) -> Unit,
) {
    LazyColumn() {
        items(algorithmsItems.size) {
            AlgorithmItem(image = painterResource(id = R.drawable.insertion_sort),
                algorithm = algorithmsItems[it],
                onClick = onClick)
        }
    }
}

@Composable
fun AlgorithmItem(
    modifier: Modifier = Modifier,
    image: Painter,
    algorithm: Algorithm,
    titleColor: Color = MaterialTheme.colors.primary,
    descriptionTextColor: Color = MaterialTheme.colors.onSurface,
    onClick: (Algorithm) -> Unit,
) {

    Box(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .clip(RoundedCornerShape(3.dp))
            .clickable { onClick(algorithm) }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = image,
                contentDescription = algorithm.name,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .shadow(5.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = algorithm.name,
                style = MaterialTheme.typography.h2,
                color = titleColor,
                modifier = Modifier.padding(7.dp)
            )

            Text(
                text = algorithm.generalInformation,
                style = MaterialTheme.typography.h3, color = descriptionTextColor,
                fontSize = 14.sp,
                modifier = Modifier,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(20.dp))

        }

    }

}