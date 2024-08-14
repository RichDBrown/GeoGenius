package com.example.geogenius.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.geogenius.R
import com.example.geogenius.ui.theme.LightBlue
import com.example.geogenius.utils.Screen
import com.example.geogenius.utils.font

@Composable
fun ResultScreen(context: Context, navController: NavController, username: String) {
    DisplayBackground(context = context)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Congrats()
        Spacer(modifier = Modifier.size(17.dp))

        Grade()
        Spacer(modifier = Modifier.size(17.dp))

        Text(text = "90% Correct", fontFamily = font, fontSize = 32.sp)
        Spacer(modifier = Modifier.size(17.dp))

        Score()
        Spacer(modifier = Modifier.size(17.dp))

        HighScores()
        Spacer(modifier = Modifier.size(40.dp))

        PlayAgain(navController)

    }
}

@Composable
private fun Congrats() {
    Text(
        text = "Congratulations XxYellowThunderxX!",
        fontFamily = font,
        fontSize = 32.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Grade() {
    Box(
        modifier = Modifier
            .background(Color.Yellow, shape = CircleShape)
            .size(203.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "A+", fontFamily = font, fontSize = 128.sp)
    }
}

@Composable
private fun Score() {
    Text(text = "You Scored 23 Pts", fontFamily = font, fontSize = 32.sp)
}

@Composable
private fun HighScores() {
    Card(
        modifier = Modifier.size(360.dp, 131.dp), shape = RoundedCornerShape(13.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = stringResource(id = R.string.high_scores),
                fontFamily = font,
                fontSize = 16.sp,
            )
            Row(
                modifier = Modifier.size(width = 335.dp, height = 20.dp),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "1 Richie", fontFamily = font, fontSize = 16.sp)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                    Text(text = "23 Pts", fontFamily = font, fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.Black)
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier.size(width = 335.dp, height = 20.dp),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "2 Logan", fontFamily = font, fontSize = 16.sp)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                    Text(text = "19 Pts", fontFamily = font, fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.Black)
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier.size(width = 335.dp, height = 20.dp),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "3 Andrew", fontFamily = font, fontSize = 16.sp)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                    Text(text = "9 Pts", fontFamily = font, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
private fun PlayAgain(navController: NavController) {
    Button(
        onClick = { navController.navigate(Screen.LoginScreen.route) },
        colors = ButtonColors(
            containerColor = LightBlue,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        modifier = Modifier.size(width = 348.dp, 73.dp)
    ) {
        Text(text = stringResource(id = R.string.play_again), fontFamily = font, fontSize = 20.sp)
    }
}