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
import com.example.geogenius.model.Player
import com.example.geogenius.ui.theme.LightBlue
import com.example.geogenius.utils.Screen
import com.example.geogenius.utils.font
import com.example.geogenius.utils.highScores

private var medal = R.drawable.gold_medal
private var grade = "A+"

/**
 * A composable function that displays a congratulatory message to the user.
 *
 * The `Congrats` function renders a text element that congratulates the user by their username.
 * The text is centered, uses a custom font, and is styled with a large font size to
 * create a prominent, celebratory message.
 *
 * @param username The name of the user to be congratulated.
 *
 * @see Text for displaying text in a Compose UI.
 * @see TextAlign.Center for centering the text horizontally.
 */
@Composable
private fun Congrats(username: String) {
    Text(
        text = "Congratulations $username",
        fontFamily = font,
        fontSize = 32.sp,
        textAlign = TextAlign.Center
    )
}

/**
 * A composable function that displays the user's grade and an associated medal image.
 *
 * The `Grade` function calculates the user's grade based on their points and displays
 * an appropriate medal image alongside the grade. The grade is determined by comparing
 * the points to predefined thresholds, and the medal image is set accordingly.
 *
 * The grade and medal are displayed inside a container with the grade text
 * centered within it.
 *
 * @param context The context used to access resources like drawable images.
 * @param points The user's score, used to determine the grade and medal.
 *
 * @see Box for creating a container with custom background and alignment.
 * @see DisplayBackground for setting the background image inside the container.
 * @see Text for displaying the grade text.
 */
@Composable
private fun Grade(context: Context, points: Int) {
    if (points >= 250) {
        medal = R.drawable.gold_medal
        grade = "A+"
    }
    else if (points > 200) {
        medal = R.drawable.silver_medal
        grade = "B"
    }
    else if (points > 150) {
        medal = R.drawable.bronze_medal
        grade = "C"
    }
    else if (points > 100) {
        medal = R.drawable.copper_medal
        grade = "D"
    } else {
        medal = R.drawable.poop_medal
        grade = "F"
    }

    Box(
        modifier = Modifier
            .background(Color.Transparent, shape = CircleShape)
            .size(203.dp),
        contentAlignment = Alignment.Center
    ) {
        DisplayBackground(drawableToBitmap = drawableToBitmap(context, medal))
        Text(text = grade, fontFamily = font, fontSize = 128.sp)
    }
}

/**
 * A composable function that displays the user's score.
 *
 * The `Score` function renders a text element that shows the user's total points scored.
 * The text is styled with a custom font and a medium-large font size to make the score
 * easily visible and prominent on the screen.
 *
 * @param points The user's total score to be displayed.
 *
 * @see Text for displaying text in a Compose UI.
 */
@Composable
private fun Score(points: Int) {
    Text(text = "You Scored $points Pts", fontFamily = font, fontSize = 32.sp)
}

/**
 * A composable function that displays a list of high scores.
 *
 * The `HighScores` function presents the top three players and their scores in a card.
 * Each player's username and score are displayed in a row, with a divider separating
 * the rows. The card has rounded corners and shadow elevation to give it a distinct
 * appearance. The high scores are arranged in descending order, with the highest score
 * at the top.
 *
 * @param topPlayers A list of the top players, with their usernames and scores.
 *
 * @see Card for creating a material design card.
 * @see Column for arranging UI elements vertically.
 * @see Row for arranging UI elements horizontally.
 * @see Text for displaying text in a Compose UI.
 * @see HorizontalDivider for adding a dividing line between rows.
 */
@Composable
private fun HighScores(topPlayers: List<Player>) {
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
                Text(text = "1 ${topPlayers[0].username}", fontFamily = font, fontSize = 16.sp)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                    Text(text = "${topPlayers[0].points} Pts", fontFamily = font, fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.Black)
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier.size(width = 335.dp, height = 20.dp),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "2 ${topPlayers[1].username}", fontFamily = font, fontSize = 16.sp)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                    Text(text = "${topPlayers[1].points} Pts", fontFamily = font, fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            HorizontalDivider(thickness = 1.dp, color = Color.Black)
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier.size(width = 335.dp, height = 20.dp),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "3 ${topPlayers[2].username}", fontFamily = font, fontSize = 16.sp)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                    Text(text = "${topPlayers[2].points} Pts", fontFamily = font, fontSize = 16.sp)
                }
            }
        }
    }
}

/**
 * A composable function that displays a "Play Again" button.
 *
 * The `PlayAgain` function creates a button that, when clicked, navigates the user
 * back to the login screen. The button is styled with custom colors and a large
 * font size to make it easy to see and interact with.
 *
 * @param navController The navigation controller used to navigate back to the login screen.
 *
 * @see Button for creating a clickable button in Compose.
 * @see NavController for managing navigation in a Compose application.
 */
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

/**
 * A composable function that displays the result screen after the user completes an activity.
 *
 * The `ResultScreen` function is responsible for presenting the final results, including
 * a congratulatory message, the user's grade and medal, the score, the list of high scores,
 * and a "Play Again" button. The function uses a column layout to arrange these components
 * vertically, centered on the screen.
 *
 * The background image is set using the `DisplayBackground` composable, and the rest of the
 * components are displayed in a visually appealing order with appropriate spacing.
 *
 * @param context The context used to access resources such as drawable images.
 * @param navController The navigation controller used to navigate to other screens.
 * @param username The username of the player, displayed in the congratulatory message.
 * @param points The total points scored by the player, used to calculate the grade.
 *
 * @see DisplayBackground for setting the background image.
 * @see Congrats for displaying the congratulatory message.
 * @see Grade for displaying the user's grade and medal.
 * @see Score for displaying the user's score.
 * @see HighScores for displaying the list of high scores.
 * @see PlayAgain for the button that navigates back to the login screen.
 */
@Composable
fun ResultScreen(context: Context, navController: NavController, username: String, points: Int) {
    DisplayBackground(drawableToBitmap(context, R.drawable.background_color))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Congrats(username)
        Spacer(modifier = Modifier.size(17.dp))

        Grade(context, points)
        Spacer(modifier = Modifier.size(17.dp))

        Score(points)
        Spacer(modifier = Modifier.size(17.dp))

        val topPlayers = highScores(Player(username, points))
        HighScores(topPlayers)
        Spacer(modifier = Modifier.size(40.dp))

        PlayAgain(navController)

    }
}