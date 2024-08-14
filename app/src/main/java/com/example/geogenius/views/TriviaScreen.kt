package com.example.geogenius.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.geogenius.R
import com.example.geogenius.ui.theme.DarkBlue
import com.example.geogenius.ui.theme.LightBlue
import com.example.geogenius.utils.Screen
import com.example.geogenius.utils.font
import com.example.geogenius.utils.questions
import kotlinx.coroutines.delay

private var listOfQuestions = questions()

@Composable
private fun CountDownIndicator(navController: NavController, username: String) {
    var progress by remember {
        mutableStateOf(1f)
    }
    CircularProgressIndicator(progress = { progress })

    LaunchedEffect(key1 = progress) {
        if (progress > .00) {
            delay(30)
            progress -= .001f
        } else {
            navController.navigate(Screen.ResultScreen.withArgs(username))
        }
    }
}

@Composable
private fun TimeLeft(secondsLeft: Int) {
    var textColor by remember {
        mutableStateOf(Color.Black)
    }
    Text(text = "$secondsLeft", fontFamily = font, fontSize = 20.sp, color = textColor)

    LaunchedEffect(key1 = secondsLeft) {
        if (secondsLeft <= 10) {
            textColor = Color.Red
        }
    }
}

@Composable
private fun CountDownUI(navController: NavController, username: String, secondsLeft: Int) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CountDownIndicator(navController, username)
        TimeLeft(secondsLeft)
    }
}

@Composable
private fun Points(points: Int, secondsLeft: Int) {
    var doublePTS by remember {
        mutableStateOf("")
    }

    Text(text = "Points: $points$doublePTS", fontFamily = font, fontSize = 20.sp)

    LaunchedEffect(key1 = secondsLeft) {
        if (secondsLeft <= 10) {
            doublePTS = " 2X"
        }
    }
}

@Composable
private fun CountryFlag(currentFlag: Int) {
    Image(
        painter = painterResource(id = currentFlag),
        contentDescription = "Country Flag",
        modifier = Modifier
            .fillMaxHeight(.29f)
            .fillMaxWidth(.844f)
    )
}

@Composable
fun OptionBox(currentOption: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 348.dp, height = 73.dp)
            .background(Color.White, RoundedCornerShape(13.dp))
            .border(
                7.dp,
                color = if (isSelected) DarkBlue else Color.Black,
                RoundedCornerShape(13.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = currentOption, fontFamily = font, fontSize = 20.sp)
    }
}

@Composable
private fun Submit(isEnabled: Boolean, onSubmit: () -> Unit) {
    Button(
        onClick = { onSubmit() },
        colors = ButtonColors(
            containerColor = LightBlue,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Black
        ),
        enabled = isEnabled,
        modifier = Modifier.size(width = 348.dp, 73.dp)
    ) {
        Text(text = stringResource(id = R.string.submit), fontFamily = font, fontSize = 20.sp)
    }
}

@Composable
fun TriviaScreen(context: Context, navController: NavController, username: String) {
    var currentQuestionIndex by remember {
        mutableStateOf(0)
    }
    val currentQuestion = listOfQuestions[currentQuestionIndex]

    var selectedOption by remember {
        mutableStateOf(0)
    }

    var secondsLeft by remember {
        mutableStateOf(41)
    }

    var points by remember {
        mutableStateOf(0)
    }

    DisplayBackground(context = context)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CountDownUI(navController, username, secondsLeft)
        Spacer(modifier = Modifier.size(19.dp))

        Points(points, secondsLeft)

        CountryFlag(currentQuestion.flag)
        Spacer(modifier = Modifier.size(21.dp))

        OptionBox(currentOption = currentQuestion.option1, isSelected = selectedOption == 1) {
            selectedOption = 1
        }
        Spacer(modifier = Modifier.size(21.dp))

        OptionBox(currentOption = currentQuestion.option2, isSelected = selectedOption == 2) {
            selectedOption = 2
        }
        Spacer(modifier = Modifier.size(21.dp))

        OptionBox(currentOption = currentQuestion.option3, isSelected = selectedOption == 3) {
            selectedOption = 3
        }
        Spacer(modifier = Modifier.size(21.dp))

        OptionBox(currentOption = currentQuestion.option4, isSelected = selectedOption == 4) {
            selectedOption = 4
        }
        Spacer(modifier = Modifier.size(21.dp))

        Submit(isEnabled = selectedOption != 0) {
            if (selectedOption == currentQuestion.correctOption) {
                if (secondsLeft <= 10) points += 20 else points += 10
            }
            currentQuestionIndex++
            selectedOption = 0
        }
    }
    LaunchedEffect(key1 = secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
    }
}