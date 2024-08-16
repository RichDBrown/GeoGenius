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
import com.example.geogenius.utils.question
import kotlinx.coroutines.delay

private var question = question()

/**
 * A composable function that displays the remaining time in seconds.
 *
 * The text color changes to red when there are 10 or fewer seconds left.
 *
 * @param secondsLeft The number of seconds remaining.
 *
 * @see Text for displaying text on the screen.
 * @see LaunchedEffect for managing side-effects based on remaining time.
 */
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

/**
 * A composable function that displays a circular progress indicator and the remaining time in seconds.
 *
 * This function creates a layout that centers both the circular progress indicator and the time left.
 *
 * @param secondsLeft The number of seconds remaining.
 *
 * @see TimeLeft for the display of remaining time.
 */
@Composable
private fun CountDownIndicator(secondsLeft: Int) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(progress = { (secondsLeft.toFloat() / 100) * 2})
        TimeLeft(secondsLeft)
    }
}

/**
 * A composable function that displays the player's current score.
 *
 * When there are 10 seconds or less left in the countdown, the points display is updated to show a "2X" multiplier.
 *
 * @param points The current score of the player.
 * @param secondsLeft The number of seconds remaining in the countdown.
 *
 * @see Text for displaying text on the screen.
 * @see LaunchedEffect for managing side-effects based on remaining time.
 */
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

/**
 * A composable function that displays the flag of a country.
 *
 * The flag image is scaled to fit a specific portion of the screen.
 *
 * @param currentFlag The resource ID of the drawable representing the country's flag.
 *
 * @see Image for displaying images in Jetpack Compose.
 * @see Modifier for applying layout and styling options.
 */
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

/**
 * A composable function that displays a clickable option box for a trivia question.
 *
 * The option box can be selected by the user, and its border color changes based on selection status.
 *
 * @param currentOption The text of the option being displayed.
 * @param isSelected Whether the option is currently selected by the user.
 * @param onClick A callback function triggered when the user clicks the option box.
 *
 * @see Box for creating a layout container.
 * @see Modifier for applying layout and styling options.
 * @see clickable for detecting user clicks.
 */
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

/**
 * A composable function that displays a submit button for submitting an answer.
 *
 * The button is only enabled when the user has selected an option.
 *
 * @param isEnabled Whether the button is enabled and can be clicked.
 * @param onSubmit A callback function triggered when the user clicks the submit button.
 *
 * @see Button for creating a clickable button.
 * @see Modifier for applying layout and styling options.
 * @see ButtonColors for customizing button colors.
 */
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

/**
 * A composable function that displays the entire trivia screen.
 *
 * The screen includes a countdown timer, score display, country flag, multiple-choice options,
 * and a submit button. It handles user interactions and updates the UI accordingly.
 *
 * @param context The context for accessing resources and displaying the background.
 * @param navController The NavController used to navigate between screens.
 * @param username The username of the player.
 *
 * @see Column for arranging UI components vertically.
 * @see LaunchedEffect for managing side-effects like countdown and option selection.
 */
@Composable
fun TriviaScreen(context: Context, navController: NavController, username: String) {
    var selectedOption by remember {
        mutableStateOf(0)
    }

    var secondsLeft by remember {
        mutableStateOf(50)
    }

    var points by remember {
        mutableStateOf(0)
    }

    DisplayBackground(drawableToBitmap(context, R.drawable.background_color))

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CountDownIndicator(secondsLeft)
        Spacer(modifier = Modifier.size(19.dp))

        Points(points, secondsLeft)

        CountryFlag(question.flag)
        Spacer(modifier = Modifier.size(21.dp))

        OptionBox(currentOption = question.option1, isSelected = selectedOption == 1) {
            selectedOption = 1
        }
        Spacer(modifier = Modifier.size(21.dp))

        OptionBox(currentOption = question.option2, isSelected = selectedOption == 2) {
            selectedOption = 2
        }
        Spacer(modifier = Modifier.size(21.dp))

        OptionBox(currentOption = question.option3, isSelected = selectedOption == 3) {
            selectedOption = 3
        }
        Spacer(modifier = Modifier.size(21.dp))

        OptionBox(currentOption = question.option4, isSelected = selectedOption == 4) {
            selectedOption = 4
        }
        Spacer(modifier = Modifier.size(21.dp))

        Submit(isEnabled = selectedOption != 0) {
            if (selectedOption == question.correctOption) {
                if (secondsLeft <= 10) points += 20 else points += 10
            }
            question = question()
            selectedOption = 0
        }
    }
    LaunchedEffect(key1 = secondsLeft) {
        if (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        } else {
            navController.navigate(Screen.ResultScreen.withArgs(username, points))
        }
    }
}