package com.example.algorithmsvisualizer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.algorithmsvisualizer.ui.theme.WorkSans

@Composable
fun DrawSortImage(
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
                style = Stroke(
                    2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 10f)
                )
            )

            //Right Big Circle
            drawCircle(
                color = Color(0xFFD6D6D6),
                radius = radius * 3,
                center = Offset(y = maxHeight / 2, x = (radius * 3.2 * 2).toFloat()),
                style = Stroke(
                    2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 10f)
                )
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

@Composable
fun Star(modifier: Modifier = Modifier, tint: Color = Color.Yellow) {
    Icon(
        painter = painterResource(id = R.drawable.ic_star),
        contentDescription = null,
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search...",
    hintColor: Color = MaterialTheme.colors.onSurface,
    searchBoxColor: Color = MaterialTheme.colors.surface,
    onTextChange: (String) -> Unit,

    ) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        var searchText by remember {
            mutableStateOf("")
        }

        var isSearchBarFocused by remember {
            mutableStateOf(false)
        }

        TextField(
            value = searchText,
            onValueChange = {
                onTextChange(it)
                searchText = it
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = searchBoxColor,
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = CircleShape,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = WorkSans,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isSearchBarFocused = it.isFocused
                },
            singleLine = true,
        )

        if (!isSearchBarFocused)
            Text(
                text = hint,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                style = MaterialTheme.typography.h3,
                fontSize = 14.sp,
                color = hintColor

            )

    }

}


