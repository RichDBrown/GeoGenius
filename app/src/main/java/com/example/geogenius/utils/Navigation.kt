package com.example.geogenius.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.geogenius.views.CountDownScreen
import com.example.geogenius.views.LoginScreen
import com.example.geogenius.views.ResultScreen
import com.example.geogenius.views.TriviaScreen

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(
            route = Screen.LoginScreen.route,
        ) {
            LoginScreen(context = context.applicationContext, navController = navController)
        }
        composable(
            route = Screen.CountDownScreen.route + "/{username}",
            arguments = listOf(navArgument("username") {
                type = NavType.StringType
                nullable = false
            })
        ) { backEntry ->
            val username = backEntry.arguments?.getString("username")
            CountDownScreen(
                context = context.applicationContext,
                navController = navController,
                username = username!!
            )
        }
        composable(
            Screen.TriviaScreen.route + "/{username}",
            arguments = listOf(navArgument("username") {
                type = NavType.StringType
                nullable = false
            })
        ) { backEntry ->
            val username = backEntry.arguments?.getString("username")
            TriviaScreen(
                context = context.applicationContext,
                navController = navController,
                username = username!!
            )
        }
        composable(
            route = Screen.ResultScreen.route + "/{username}",
            arguments = listOf(navArgument("username") {
                type = NavType.StringType
                nullable = false
            })
        ) { backEntry ->
            val username = backEntry.arguments?.getString("username")
            ResultScreen(
                context = context.applicationContext,
                navController = navController,
                username = username!!
            )
        }
    }
}