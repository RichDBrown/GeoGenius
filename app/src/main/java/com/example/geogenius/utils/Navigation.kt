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

/**
 * Composable function that sets up the navigation structure for the app.
 *
 * @param context The application context, used to pass down to the different screens.
 *
 * This function creates a [NavHost] with a navigation controller and defines the routes for
 * the various screens in the app. It handles navigation between the following screens:
 * - LoginScreen: The initial screen where users log in.
 * - CountDownScreen: The screen that shows a countdown, which requires a `username` parameter.
 * - TriviaScreen: The screen where the trivia game is played, also requiring a `username` parameter.
 * - ResultScreen: The screen that shows the results of the trivia game, requiring both a `username`
 *   and `points` parameter.
 *
 * The `context` is passed down to each screen as the `applicationContext`, ensuring that
 * no memory leaks occur by holding a reference to an activity or other context types.
 *
 * The navigation structure uses named arguments in the routes to pass data between screens.
 * These arguments include:
 * - `username` (String): The player's username, passed to `CountDownScreen`, `TriviaScreen`, and `ResultScreen`.
 * - `points` (Int): The player's score, passed to `ResultScreen`.
 */
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
            route = Screen.ResultScreen.route + "/{username}/{points}",
            arguments = listOf(navArgument("username") {
                type = NavType.StringType
                nullable = false
            }, navArgument("points") {
                type = NavType.IntType
                nullable = false
            })
        ) { backEntry ->
            val username = backEntry.arguments?.getString("username")
            val points = backEntry.arguments?.getInt("points")
            ResultScreen(
                context = context.applicationContext,
                navController = navController,
                username = username!!,
                points = points!!
            )
        }
    }
}