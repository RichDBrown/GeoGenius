package com.example.geogenius.views


import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.geogenius.R
import com.example.geogenius.ui.theme.DarkBlue
import com.example.geogenius.utils.Screen
import com.example.geogenius.utils.font

@Composable
private fun TitleText() {
    Text(
        text = stringResource(R.string.login_title),
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        fontFamily = font,
    )
}

@Composable
private fun DescriptionCard() {
    Card(
        shape = RoundedCornerShape(13.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .size(360.dp, 73.dp)
    ) {
        Text(
            text = stringResource(R.string.login_description),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontFamily = font,
            modifier = Modifier.padding(19.dp, 17.dp)
        )
    }
}

@Composable
private fun LoginCard(navController: NavController) {
    Card(
        shape = RoundedCornerShape(13.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .size(360.dp, 211.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.login_instructions),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = font,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            var username by remember {
                mutableStateOf("")
            }

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Name") },
                singleLine = true,
                textStyle = TextStyle(fontFamily = font)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp, 16.dp), contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    onClick = { navController.navigate(Screen.CountDownScreen.withArgs(username)) },
                    colors = ButtonColors(
                        containerColor = DarkBlue,
                        contentColor = Color.White,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Gray
                    ),
                    enabled = username.isNotBlank()
                ) {
                    Text(text = "Let's Go", fontFamily = font, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(context: Context, navController: NavController) {
    DisplayBackground(context = context)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText()
        Spacer(modifier = Modifier.size(34.dp))

        DescriptionCard()
        Spacer(modifier = Modifier.size(34.dp))

        LoginCard(navController)
    }
}