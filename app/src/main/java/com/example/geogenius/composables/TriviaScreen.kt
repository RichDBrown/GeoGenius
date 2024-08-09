package com.example.geogenius.composables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geogenius.R
import com.example.geogenius.ui.theme.LightBlue
import com.example.geogenius.utils.font
import kotlinx.coroutines.delay

@Composable
fun TriviaScreen(context: Context) {
    DisplayBackground(context = context)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CountDown()
        Spacer(modifier = Modifier.size(19.dp))

        Points()

        CountryFlag()
        Spacer(modifier = Modifier.size(21.dp))

        Choices()
        Spacer(modifier = Modifier.size(21.dp))

        Submit()
    }
}

@Composable
private fun CountDown() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CountDownIndicator()
        TimeLeft()
    }
}

@Composable
private fun CountDownIndicator() {
    var progress by remember {
        mutableStateOf(1f)
    }
    CircularProgressIndicator(progress = { progress })

    LaunchedEffect(key1 = progress) {
        if (progress > .00) {
            delay(30)
            progress -= .001f
        }
    }
}

@Composable
private fun TimeLeft() {
    var secondsLeft by remember {
        mutableStateOf(41)
    }

    var textColor by remember {
        mutableStateOf(Color.Black)
    }
    Text(text = "$secondsLeft", fontFamily = font, fontSize = 20.sp, color = textColor)

    LaunchedEffect(key1 = secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
            if (secondsLeft <= 10) {
                textColor = Color.Red
            }
        }
    }
}

@Composable
private fun Points() {
    var secondsLeft by remember {
        mutableStateOf(41)
    }
    var points by remember {
        mutableStateOf(0)
    }
    var doublePTS by remember {
        mutableStateOf("")
    }

    Text(text = "Points: $points$doublePTS", fontFamily = font, fontSize = 20.sp)

    LaunchedEffect(key1 = secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
            if (secondsLeft <= 10) {
                doublePTS = " 2X"
            }
        }
    }
}

@Composable
private fun CountryFlag() {
    Image(
        painter = painterResource(id = R.drawable.ae),
        contentDescription = "Flag",
        modifier = Modifier
            .fillMaxHeight(.29f)
            .fillMaxWidth(.844f)
    )
}

@Composable
private fun Choices() {
    Box(
        modifier = Modifier
            .size(width = 348.dp, height = 73.dp)
            .background(Color.White, RoundedCornerShape(13.dp))
            .border(7.dp, Color.Black, RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Afghanistan", fontFamily = font, fontSize = 20.sp)
    }
    Spacer(modifier = Modifier.size(21.dp))

    Box(
        modifier = Modifier
            .size(width = 348.dp, height = 73.dp)
            .background(Color.White, RoundedCornerShape(13.dp))
            .border(7.dp, Color.Black, RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Afghanistan", fontFamily = font, fontSize = 20.sp)
    }
    Spacer(modifier = Modifier.size(21.dp))

    Box(
        modifier = Modifier
            .size(width = 348.dp, height = 73.dp)
            .background(Color.White, RoundedCornerShape(13.dp))
            .border(7.dp, Color.Black, RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Afghanistan", fontFamily = font, fontSize = 20.sp)
    }
    Spacer(modifier = Modifier.size(21.dp))

    Box(
        modifier = Modifier
            .size(width = 348.dp, height = 73.dp)
            .background(Color.White, RoundedCornerShape(13.dp))
            .border(7.dp, Color.Black, RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Afghanistan", fontFamily = font, fontSize = 20.sp)
    }
}

@Composable
private fun Submit() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonColors(
            containerColor = LightBlue,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        modifier = Modifier.size(width = 348.dp, 73.dp)
    ) {
        Text(text = stringResource(id = R.string.submit), fontFamily = font, fontSize = 20.sp)
    }
}