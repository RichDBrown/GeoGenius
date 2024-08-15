package com.example.geogenius.views

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.geogenius.R
import com.example.geogenius.utils.Screen
import com.example.geogenius.utils.font
import kotlinx.coroutines.delay

/**
 * Composable function that displays a countdown timer and navigates to the trivia screen upon completion.
 *
 * This function sets up a background image using the [DisplayBackground] composable with a drawable
 * resource. It then displays a countdown timer in the center of the screen.
 *
 * @param context The context used to retrieve the background drawable resource.
 * @param navController The [NavController] used to handle navigation to the next screen.
 * @param username The username to be passed to the trivia screen.
 */

@Composable
fun CountDownScreen(context: Context, navController: NavController, username: String) {
    DisplayBackground(drawableToBitmap(context, R.drawable.background_color))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var count by remember {
            mutableStateOf(3)
        }

        Text(text = "$count", fontFamily = font, fontSize = 128.sp)

        LaunchedEffect(key1 = count) {
            if (count > 1) {
                delay(1000)
                count--
            } else {
                delay(1000)
                navController.navigate(Screen.TriviaScreen.withArgs(username))
            }
        }
    }
}