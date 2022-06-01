package com.example.algorithmsvisualizer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.algorithmsvisualizer.data.model.Algorithm
import com.example.algorithmsvisualizer.data.model.AlgorithmCode
import com.example.algorithmsvisualizer.ui.theme.WorkSans
import com.example.algorithmsvisualizer.viewmodel.AlgorithmViewModel

@Composable
fun AlgorithmVisualizerScreen(
    algorithmId: Int,
    viewModel: AlgorithmViewModel,
    navController: NavController,
) {
    val algorithm = viewModel.algorithmState.value
    val codes = viewModel.algorithmCodes.value


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(name = algorithm.name, timeComplexity = algorithm.timeComplexity) {
                navController.navigateUp()
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            BottomBar(
                modifier = Modifier.fillMaxWidth(),
                onPlayPauseClick = { /*TODO*/ },
                onNextStepClick = { /*TODO*/ },
                onBackStepClick = { /*TODO*/ },
                onSpeedUpClick = { /*TODO*/ },
                onSlowDownClick = { /*TODO*/ },
                isPlaying = false

            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = bottomPadding)
        ) {
            VisualizerSection(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxWidth()

            )

            TabBarSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
                ,
                tabs = listOf(
                    TabItem(icon = painterResource(id = R.drawable.ic_text), "Description"),
                    TabItem(icon = painterResource(id = R.drawable.ic_code), "Code"),
                ),
                code = if (codes.isNotEmpty()) codes.first() else AlgorithmCode("", 0, ""),
                algorithm.generalInformation
            )
        }


    }

}

@Composable
fun VisualizerSection(
    modifier: Modifier = Modifier
) {


    Box(modifier = modifier) {
        Text(text = "Testy", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun TabBarSection(
    modifier: Modifier = Modifier,
    tabs: List<TabItem>,
    code: AlgorithmCode,
    algorithmDescription: String
) {

    var selectedTab by remember {
        mutableStateOf(1)
    }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {

            TabRow(
                selectedTabIndex = selectedTab,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.surface,
                modifier = Modifier.fillMaxSize()
            ) {
                tabs.forEachIndexed { i, tab ->
                    TabItem(tab = tab, onClick = { selectedTab = i }, isSelected = i == selectedTab)
                }
            }
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top) {
            SelectionContainer {
                Text(
                    text = if (selectedTab == 0) algorithmDescription else code.code,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, bottom = 15.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Light
                )
            }
        }

    }

}

@Composable
fun TabItem(
    tab: TabItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Box(modifier = modifier.clickable { onClick() }, contentAlignment = Alignment.Center) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = tab.icon, contentDescription = null,
                tint = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                modifier = Modifier.size(25.dp)
            )

            Text(
                text = tab.title,
                color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                fontFamily = WorkSans,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp
            )


        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .align(BottomCenter)
                .background(if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface)
        )
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

            Text(
                text = name,
                style = MaterialTheme.typography.h2,
                color = Color.White,
                fontSize = 18.sp
            )

            Text(
                text = timeComplexity,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 10.dp)
            )


        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onPlayPauseClick: () -> Unit,
    onNextStepClick: () -> Unit,
    onBackStepClick: () -> Unit,
    onSpeedUpClick: () -> Unit,
    onSlowDownClick: () -> Unit,
    isPlaying: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.surface
) {

    BottomAppBar(
        backgroundColor = backgroundColor,
        modifier = Modifier.height(75.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {

            IconButton(onClick = { onSlowDownClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(16.dp)
                )
            }

            IconButton(onClick = { onPlayPauseClick() }) {
                Icon(
                    painter = if (!isPlaying) painterResource(id = R.drawable.ic_play).also {
                        Log.d(
                            "test",
                            "test"
                        )
                    } else painterResource(
                        id = R.drawable.ic_pause
                    ),
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            IconButton(onClick = { onSpeedUpClick() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = { onBackStepClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_left_arrow),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(19.dp)
                )
            }

            Button(
                onClick = { onNextStepClick() },
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                modifier = Modifier.shadow(3.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.fillMaxHeight(0.5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Next Step",
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.surface,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_right_arrow),
                        contentDescription = null,
                        tint = MaterialTheme.colors.surface,
                        modifier = Modifier.size(19.dp)
                    )

                }
            }
        }
    }

}