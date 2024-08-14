package com.example.geogenius.model

data class Question(
    val flag: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctOption: Int,
)