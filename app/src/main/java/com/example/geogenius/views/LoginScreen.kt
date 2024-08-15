package com.example.geogenius.views


import android.content.Context
import android.widget.Toast
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

/**
 * A composable function that displays Welcome to GeoGenius centered on the screen.
 *
 * This function uses a string resource for the text, sets the font size to 40sp,
 * centers the text, and applies a custom font family.
 *
 * @see stringResource for fetching the localized string.
 * @see TextAlign.Center for aligning the text in the center.
 * @see font for the custom font family used.
 */
@Composable
private fun TitleText() {
    Text(
        text = stringResource(R.string.login_title),
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        fontFamily = font,
    )
}

/**
 * A composable function that displays the description of the game inside a styled card.
 *
 * @see Card for creating a material design card.
 * @see RoundedCornerShape for rounding the corners of the card.
 * @see stringResource for fetching the localized string.
 * @see TextAlign.Center for aligning the text in the center.
 * @see Modifier for applying layout and styling.
 */
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

/**
 * A composable function that displays a login card with a text field and a button.
 *
 * This function creates a card that contains a column layout with instructions,
 * an input field for the username, and a button for to navigate to the next screen. The card has rounded corners
 * and a shadow elevation to enhance its appearance.
 *
 * The text displayed at the top of the card provides instructions to the user,
 * which are fetched from a string resource. Below the instructions, there is an
 * outlined text field for the user to enter their name. The entered name is stored
 * in a mutable state and is used to enable the "Let's Go" button, which is located
 * at the bottom right of the card.
 *
 * When the "Let's Go" button is clicked, the user is navigated to the next screen
 * `CountDownScreen` with the entered username passed as an argument.
 *
 * @param navController The navigation controller used to handle navigation between screens.
 *
 * @see Card for creating a material design card.
 * @see Column for arranging UI elements vertically.
 * @see OutlinedTextField for creating an input field with a border.
 * @see Button for creating a clickable button.
 * @see NavController for navigating between screens in a Compose application.
 */
@Composable
private fun LoginCard(context: Context, navController: NavController) {
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
                    onClick = {
                        if (username.isEmpty()) {
                            Toast.makeText(context, "Please enter a username", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            navController.navigate(Screen.CountDownScreen.withArgs(username))
                        }
                    },
                    colors = ButtonColors(
                        containerColor = DarkBlue,
                        contentColor = Color.White,
                        disabledContentColor = Color.Black,
                        disabledContainerColor = Color.Gray
                    )
                ) {
                    Text(text = "Let's Go", fontFamily = font, fontSize = 14.sp)
                }
            }
        }
    }
}

/**
 * A composable function that displays the login screen of the application.
 *
 * The `LoginScreen` function is responsible for assembling and displaying the UI
 * components that make up the login screen. It includes the background image, the title,
 * the description, and the login card. The function uses a column layout to arrange
 * these components vertically in the center of the screen.
 *
 * The background image is displayed using the `DisplayBackground` composable, which
 * converts a drawable resource to a bitmap and sets it as the background. The screen
 * content includes:
 * - `TitleText`: Displays the screen's title at the top.
 * - `DescriptionCard`: Provides description of the game.
 * - `LoginCard`: Contains the login card where the user can enter their username
 *   and navigate to the next screen using the provided `NavController`.
 *
 * @param context The context from which to access resources such as drawables.
 * @param navController The navigation controller used to handle navigation to other screens.
 *
 * @see DisplayBackground for setting the background image.
 * @see TitleText for displaying the title.
 * @see DescriptionCard for displaying the description.
 * @see LoginCard for the login card and navigation handling.
 */
@Composable
fun LoginScreen(context: Context, navController: NavController) {
    DisplayBackground(drawableToBitmap(context, R.drawable.background_color))

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText()
        Spacer(modifier = Modifier.size(34.dp))

        DescriptionCard()
        Spacer(modifier = Modifier.size(34.dp))

        LoginCard(context, navController)
    }
}