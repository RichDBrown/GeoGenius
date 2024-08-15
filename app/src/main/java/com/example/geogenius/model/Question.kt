package com.example.geogenius.model

import androidx.annotation.DrawableRes


/**
 * Data class representing a quiz question.
 *
 * @property flag The resource ID of the drawable representing the question's flag or image.
 *                Must be a valid drawable resource annotated with @DrawableRes.
 * @property option1 The first answer option as a String.
 * @property option2 The second answer option as a String.
 * @property option3 The third answer option as a String.
 * @property option4 The fourth answer option as a String.
 * @property correctOption The index of the correct answer option (1-based index, where 1 = option1, 2 = option2, etc.).
 */
data class Question(
    @DrawableRes val flag: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctOption: Int,
)