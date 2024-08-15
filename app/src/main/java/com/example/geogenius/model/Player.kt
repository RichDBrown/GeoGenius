package com.example.geogenius.model

/**
 * A data class representing a player in the trivia game.
 *
 * The [Player] class holds the player's username and their total points in the game.
 * This class is used to keep track of the players' scores to display leaderboards.
 *
 * @property username The name of the player.
 * @property points The total score of the player.
 */
data class Player(val username: String, val points: Int)