package com.example.geogenius.utils

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.geogenius.R
import com.example.geogenius.model.Player
import com.example.geogenius.model.Question
import com.example.geogenius.model.countries
import kotlin.random.Random

val font = FontFamily(
    Font(R.font.russo_one)
)

/**
 * A sealed class representing the different screens in the app's navigation flow.
 *
 * Each object within this sealed class corresponds to a specific screen in the app, with a unique [route]
 * that identifies the screen. The `withArgs` function allows for appending arguments to the route for
 * navigation purposes.
 *
 * @property route The route associated with the screen, used for navigation.
 */
sealed class Screen(val route: String) {
    data object LoginScreen : Screen("LoginScreen")
    data object CountDownScreen : Screen("CountDownScreen")
    data object TriviaScreen : Screen("TriviaScreen")
    data object ResultScreen : Screen("ResultScreen")

    /**
     * Appends the provided arguments to the screen's route for navigation.
     *
     * This function allows you to create a navigation route with dynamic arguments by appending
     * the provided [args] to the screen's base route.
     *
     * @param args The arguments to be appended to the route.
     * @return A string representing the complete route with arguments.
     */
    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}

/**
 * Generates a list of four random and unique country names.
 *
 * This function creates a mutable list of country names by randomly selecting four unique names
 * from the set of available country names. Each selected country name is added to the list and
 * then removed from the set to ensure no duplicates. The resulting list of four country names is returned.
 *
 * @return a [MutableList] of four unique country names, randomly selected from the available countries.
 *
 * @see countries for the map of all available country names and their corresponding data
 */
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

/**
 * Generates a random multiple-choice question about countries and their flags.
 *
 * This function creates a list of options representing country names, selects one of the options
 * as the correct answer, and constructs a [Question] object. The correct option is randomly selected
 * from the list of options, and its index is used to retrieve the corresponding country flag.
 * The [Question] object is then returned, containing the flag, four country options, and the correct option index.
 *
 * @return a [Question] object containing a randomly selected country flag, four country name options,
 * and the index of the correct option.
 *
 * @see Question for the data structure used to represent the question
 */
fun question(): Question {
    val options = options()
    val correctOption = Random.nextInt(4)
    val question = Question(
        countries[options.elementAt(correctOption)]!!,
        options[0],
        options[1],
        options[2],
        options[3],
        correctOption + 1
    )
    return question
}

private val listOfPlayers = mutableStateListOf<Player>()

/**
 * Updates and returns a list of the top 3 players based on their scores.
 *
 * This function checks if the given [player] is already in the list of players.
 * If the player is not present, they are added to the list. The list is then
 * sorted in descending order by the player's points, and if the list contains fewer
 * than 3 players, it is padded with dummy players (with an empty name and 0 points).
 *
 * The function returns the top 3 players from the sorted list.
 *
 * @param player the [Player] to be added to the high scores list
 * @return a [List] of the top 3 [Player]s based on their points
 *
 * @see Player for the player data model
 */
fun highScores(player: Player): List<Player> {
    if (!listOfPlayers.contains(player)) {
        listOfPlayers.add(player)
    }

    listOfPlayers.sortByDescending { it.points }

    //Needed to prevent index out-of-bounds exception in ResultsScreen
    while (listOfPlayers.size < 3) {
        listOfPlayers.add(Player("", 0))
    }

    return listOfPlayers.take(3)
}