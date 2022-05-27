package com.example.algorithmsvisualizer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.algorithmsvisualizer.data.model.Algorithm

@Composable
fun AlgorithmsListScreen(
    navController: NavController,
) {


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AlgorithmList(algorithmList = listOf(
            Algorithm("Sort", 3)
        ), onClick = {})
    }

}

@Composable
fun AlgorithmList(
    modifier: Modifier = Modifier,
    algorithmList: List<Algorithm>,
    onClick: (Algorithm) -> Unit,
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 30.dp, bottom = 15.dp, start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(algorithmList.size) {
            AlgorithmItem(algorithm = algorithmList[it], onClick = onClick)
        }

    }


}

@Composable
fun AlgorithmItem(
    modifier: Modifier = Modifier,
    algorithm: Algorithm,
    onClick: (Algorithm) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { onClick(algorithm) }
            .background(MaterialTheme.colors.surface)
            .height(100.dp)
            .clip(RoundedCornerShape(2.dp)),
    ) {


        Star(
            modifier = Modifier
                .align(TopCenter)
                .padding(top = 10.dp, start = 80.dp)
                .size(12.dp)
                .rotate(20f)
        )

        Star(
            modifier = Modifier
                .align(BottomEnd)
                .padding(bottom = 10.dp, end = 10.dp)
                .size(15.dp)
                .rotate(50f),
            tint = MaterialTheme.colors.primary

        )

        Star(
            modifier = Modifier
                .align(BottomCenter)
                .padding(start = 80.dp, bottom = 10.dp)
                .size(12.dp)
                .rotate(-50f),
        )

        Star(
            modifier = Modifier
                .align(TopEnd)
                .padding(end = 10.dp, top = 10.dp)
                .size(12.dp)
                .rotate(-50f),
        )

        Star(
            modifier = Modifier
                .align(Center)
                .padding(start = 50.dp)
                .size(15.dp),
            tint = MaterialTheme.colors.primary
        )

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 15.dp)) {

            Column(modifier = Modifier
                .weight(2f)
                .align(CenterVertically)
            ) {

                Text(text = algorithm.name,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onSurface)

                Text(text = algorithm.algorithmsCount.toString().plus(" algorithms"),
                    style = MaterialTheme.typography.h3, color = MaterialTheme.colors.primary,
                    modifier = Modifier)
            }

            Row(modifier = Modifier
                .weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                val radius = 32f
                DrawSortImage(radius = radius, modifier = Modifier)
            }

        }

    }
}





