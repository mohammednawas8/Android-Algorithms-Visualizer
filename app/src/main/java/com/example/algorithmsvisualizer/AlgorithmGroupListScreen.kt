package com.example.algorithmsvisualizer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.algorithmsvisualizer.data.db.relations.AlgorithmGroupWithAlgorithms
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.data.model.AlgorithmGroup
import com.example.algorithmsvisualizer.viewmodel.ScreensViewModel

@Composable
fun AlgorithmGroupListScreen(
    viewModel: ScreensViewModel,
    onClick: (groupId: Int,algorithmList: List<Algorithm>) -> Unit
) {

    val algorithmGroupStateList =
        viewModel.algorithmGroupWithAlgorithms.collectAsState(initial = emptyList())


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
            SearchBar(
                onTextChange = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            )
        AlgorithmList(
            algorithmList = algorithmGroupStateList.value,
            modifier = Modifier.padding(top = 18.dp)
        ) { groupId,algorithmList ->
            onClick(groupId, algorithmList)
        }
    }

}

@Composable
fun AlgorithmList(
    modifier: Modifier = Modifier,
    algorithmList: List<AlgorithmGroupWithAlgorithms>,
    onClick: (groupId: Int,List<Algorithm>) -> Unit,
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues( bottom = 15.dp, start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {


        items(algorithmList.size) {
            val algorithmGroupWithAlgorithms = algorithmList[it]
            AlgorithmGroupItem(algorithmGroup = algorithmList[it].algorithmGroup, count = algorithmGroupWithAlgorithms.algorithms.size,onClick = {
                onClick(algorithmGroupWithAlgorithms.algorithmGroup.groupId,algorithmGroupWithAlgorithms.algorithms)
            })
        }

    }


}

@Composable
fun AlgorithmGroupItem(
    modifier: Modifier = Modifier,
    algorithmGroup: AlgorithmGroup,
    count: Int,
    onClick: (AlgorithmGroup) -> Unit,
) {
    Box(modifier = modifier
        .clickable { onClick(algorithmGroup) }
        .background(MaterialTheme.colors.surface)
        .height(100.dp)
        .clip(RoundedCornerShape(2.dp)),
        contentAlignment = Center) {

//        Icon(
//            painter = painterResource(id = R.drawable.ic_star),
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .padding(start = 40.dp, top = 10.dp)
//                .size(15.dp),
//            contentDescription = null,
//            tint = Color.Yellow
//        )
//
//        Icon(
//            painter = painterResource(id = R.drawable.ic_star),
//            modifier = Modifier
//                .align(Alignment.CenterEnd)
//                .padding(bottom = 40.dp, end = 16.dp)
//                .size(15.dp),
//            contentDescription = null,
//            tint = Color.Yellow
//        )
//
//        Icon(
//            painter = painterResource(id = R.drawable.ic_star),
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(start = 55.dp, top = 10.dp, bottom = 8.dp)
//                .size(15.dp),
//            contentDescription = null,
//            tint = Color.Yellow
//        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {

            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .align(CenterVertically)
            ) {

                Text(
                    text = algorithmGroup.name,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.primary
                )

                Text(
                    text = count.toString().plus(" algorithms"),
                    style = MaterialTheme.typography.h3, color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                )
            }

            Row(
                modifier = Modifier.weight(0.3f),
                horizontalArrangement = Arrangement.End
            ) {
                val radius = 32f
                DrawSortImage(radius = radius)
            }

        }

    }
}




