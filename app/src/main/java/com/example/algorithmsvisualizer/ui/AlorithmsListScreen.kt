package com.example.algorithmsvisualizer.ui

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
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.algorithmsvisualizer.R
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.data.model.AlgorithmGroup

@Composable
fun AlgorithmsListScreen(
    navController: NavController,
) {


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

//        AlgorithmList(algorithmList = listOf(
//            Algorithm("Sort", 3),
//        ), onClick = {})
    }

}

@Composable
fun AlgorithmList(
    modifier: Modifier = Modifier,
    algorithmList: List<AlgorithmGroup>,
    onClick: (AlgorithmGroup) -> Unit,
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 30.dp, bottom = 15.dp, start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        items(algorithmList.size) {
            AlgorithmItem(algorithmGroup = algorithmList[it] ,onClick = onClick)
        }

    }


}

@Composable
fun AlgorithmItem(
    modifier: Modifier = Modifier,
    algorithmGroup: AlgorithmGroup,
    onClick: (AlgorithmGroup) -> Unit,
    ) {
    Box(modifier = modifier
        .clickable { onClick(algorithmGroup) }
        .background(MaterialTheme.colors.surface)
        .height(100.dp)
        .clip(RoundedCornerShape(2.dp)),
        contentAlignment = Center) {

        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 40.dp, top = 10.dp)
                .size(15.dp),
            contentDescription = null,
            tint = Color.Yellow
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(bottom = 40.dp, end = 16.dp)
                .size(15.dp),
            contentDescription = null,
            tint = Color.Yellow
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 55.dp, top = 10.dp, bottom = 8.dp)
                .size(15.dp),
            contentDescription = null,
            tint = Color.Yellow
        )

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 15.dp)) {

            Column(modifier = Modifier
                .weight(1f)
                .align(CenterVertically)
            ) {

                Text(text = algorithmGroup.name,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onSurface)

                Text(text = algorithmGroup.count.toString().plus(" algorithms"),
                    style = MaterialTheme.typography.h3, color = MaterialTheme.colors.primary,
                    modifier = Modifier)
            }

            Row(modifier = Modifier
                .weight(1f)
                .padding(start = 30.dp),
                horizontalArrangement = Arrangement.End
            ) {
                val radius = 32f
                SortImage(radius = radius)
            }

        }

    }
}

@Composable
fun SortImage(
    modifier: Modifier = Modifier,
    radius: Float = 35f,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val maxHeight = constraints.maxHeight.toFloat()
        val maxWidth = constraints.maxWidth.toFloat()



        Canvas(modifier = modifier, onDraw = {

            //Left Big Circle
            drawCircle(
                color = Color(0xFFD6D6D6),
                radius = radius * 3,
                center = Offset(y = maxHeight / 2, x = (radius * 3.2).toFloat()),
                style = Stroke(2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 10f))
            )

            //Right Big Circle
            drawCircle(
                color = Color(0xFFD6D6D6),
                radius = radius * 3,
                center = Offset(y = maxHeight / 2, x = (radius * 3.2 * 2).toFloat()),
                style = Stroke(2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 10f))
            )

            //First circle from left
            drawCircle(
                color = Color(0xFF4eeaca),
                radius = radius,
                center = Offset(y = maxHeight / 2, x = 0f)
            )

            //Second circle from left
            drawCircle(
                color = Color(0xFF4e9796),
                radius = radius - 4,
                center = Offset(y = maxHeight / 2, x = (radius * 3.2).toFloat()),
            )

            //Third circle from left
            drawCircle(
                color = Color(0xFF9a84d6),
                radius = radius,
                center = Offset(y = maxHeight / 2, x = (radius * 3.2 * 2).toFloat()),
            )

            //Fourth circle from left
            drawCircle(
                color = Color(0xFF816568),
                radius = radius - 4,
                center = Offset(y = maxHeight / 2, x = (radius * 3.2 * 3).toFloat()),
            )


        })
    }

}


