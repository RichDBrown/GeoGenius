package com.example.geogenius.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.geogenius.R
import com.example.geogenius.model.Question
import com.example.geogenius.model.countries
import kotlin.random.Random

val font = FontFamily(
    Font(R.font.russo_one)
)

sealed class Screen(val route: String) {
    data object LoginScreen : Screen("LoginScreen")
    data object CountDownScreen : Screen("CountDownScreen")
    data object TriviaScreen : Screen("TriviaScreen")
    data object ResultScreen : Screen("ResultScreen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}

fun questions(): List<Question> {
    val questions = mutableListOf<Question>()
    var options: List<String>
    var correctOption: Int
    for (i in 1..100) {
        options = options()
        correctOption = Random.nextInt(4)
        val question = Question(
            countries[options.elementAt(correctOption)]!!,
            options[0],
            options[1],
            options[2],
            options[3],
            correctOption + 1
        )
        questions.add(question)
    }
    return questions
}

fun options(): MutableList<String> {
    val options = mutableListOf<String>()

    val countryNamesSet = countries.keys.toMutableSet()
    for (i in 1..4) {
        val randomIndex = Random.nextInt(countryNamesSet.size)
        val randomCountryName = countryNamesSet.elementAt(randomIndex)
        options.add(randomCountryName)
        countryNamesSet.remove(randomCountryName)
    }
    return options
}