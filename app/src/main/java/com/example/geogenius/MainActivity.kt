package com.example.geogenius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.geogenius.composables.LoginScreen
import com.example.geogenius.composables.ResultScreen
import com.example.geogenius.composables.TriviaScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen(context = this)
            //CountDownScreen(context = this)
            //TriviaScreen(context = this)
            //ResultScreen(context = this)
        }
    }
}
