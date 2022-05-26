//package com.example.algorithmsvisualizer
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.material.TextField
//import androidx.compose.material.TextFieldDefaults
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.focus.onFocusChanged
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.example.algorithmsvisualizer.data.model.Algorithm
//
//@Composable
//fun SearchBar(
//    modifier: Modifier = Modifier,
//    hint: String = "Search...",
//    hintColor: Color = MaterialTheme.colors.onBackground,
//    onTextChange: (String) -> Unit,
//) {
//
//    Box(
//        modifier = modifier,
//        contentAlignment = Alignment.Center
//    ) {
//
//        var searchText by remember {
//            mutableStateOf("")
//        }
//
//        var isSearchBarFocused by remember {
//            mutableStateOf(false)
//        }
//
//        TextField(
//            value = searchText,
//            onValueChange = {
//                onTextChange(it)
//                searchText = it
//            },
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = MaterialTheme.colors.surface,
//                textColor = MaterialTheme.colors.onSurface,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            ),
//            shape = CircleShape,
//            textStyle = MaterialTheme.typography.h2,
//            modifier = Modifier
//                .fillMaxWidth()
//                .onFocusChanged {
//                    isSearchBarFocused = it.isFocused
//                },
//            singleLine = true,
//        )
//
//        if (!isSearchBarFocused)
//            Text(
//                text = hint,
//                textAlign = TextAlign.Start,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 12.dp),
//                style = MaterialTheme.typography.h2,
//                color = hintColor
//
//            )
//
//    }
//
//}
//
//
//@Composable
//fun AlgorithmCard(
//    algorithm: Algorithm,
//    modifier: Modifier = Modifier,
//    cardColor: Color = MaterialTheme.colors.surface,
//    onClick: (Algorithm) -> Unit,
//) {
//    Box(
//        modifier = modifier
//            .background(cardColor)
//            .clickable { onClick(algorithm) }
//            .shadow(5.dp)
//            .clip(RoundedCornerShape(8.dp)),
//        contentAlignment = Alignment.BottomCenter,
//
//        ) {
//
//        Image(
//            painter = algorithm.image,
//            contentDescription = algorithm.name,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    Brush.verticalGradient(
//                        listOf(
//                            Color.Transparent,
//                            MaterialTheme.colors.primaryVariant
//                        )
//                    )
//                ),
//            horizontalAlignment = Alignment.Start
//        ) {
//
//            Text(
//                text = algorithm.name,
//                style = MaterialTheme.typography.h2,
//                color = MaterialTheme.colors.onSurface,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(horizontal = 16.dp).shadow(5.dp)
//            )
//
//            Text(
//                text = algorithm.details.shortBrief,
//                style = MaterialTheme.typography.body1,
//                color = MaterialTheme.colors.onSurface,
//                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
//            )
//
//        }
//
//
//    }
//}
//
