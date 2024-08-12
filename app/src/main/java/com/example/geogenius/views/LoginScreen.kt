package com.example.geogenius.composables


import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geogenius.R
import com.example.geogenius.ui.theme.DarkBlue
import com.example.geogenius.utils.font
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(context: Context) {
    DisplayBackground(context = context)

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TitleText()
        Spacer(modifier = Modifier.size(34.dp))

        DescriptionCard()
        Spacer(modifier = Modifier.size(34.dp))

        LoginCard()
    }
}

@Composable
fun MovingLazyRow(vectorDrawables: List<Int>) {
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        while (true) {
            // Automatically scroll by 100 pixels over 500 milliseconds
            listState.animateScrollBy(100f, tween(500))
            delay(1000L) // Add delay if needed between scrolls
        }
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(vectorDrawables) { drawableRes ->
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

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
private fun LoginCard() {
    Card(
        shape = RoundedCornerShape(13.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .size(360.dp, 211.dp)
    ) {
        Column(
            modifier = Modifier.size(360.dp, 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.login_instructions),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = font,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            UsernameTextField()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 16.dp), contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonColors(
                    containerColor = DarkBlue,
                    contentColor = Color.White,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                )
            ) {
                Text(text = "Let's Go", fontFamily = font, fontSize = 14.sp)
            }
        }
    }
}

@Composable
private fun UsernameTextField() {
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
}