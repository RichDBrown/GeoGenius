package com.example.geogenius.composables

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
import com.example.geogenius.utils.font
import kotlinx.coroutines.delay

@Composable
fun CountDownScreen(context: Context) {
    DisplayBackground(context = context)
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
            }
        }
    }
}