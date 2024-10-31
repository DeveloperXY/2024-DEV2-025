package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.domain.GameState
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import org.junit.Before
import org.junit.Test

class GameStateTests {

    private lateinit var gameState: GameState

    @Before
    fun setUp() {
        gameState = GameState()
    }

    @Test
    fun initialGameStateIsCorrect() {
        gameState.apply {
            currentPlayer.shouldBeEqualComparingTo("X")
            gameBoard.isClear().shouldBeTrue()
        }
    }

    @Test
    fun `Making a move updates the game board and toggles current player`() {
        gameState.apply {
            makeMove(at = 0 to 0)
            currentPlayer.shouldBeEqualComparingTo("O")

            makeMove(at = 1 to 1)
            currentPlayer.shouldBeEqualComparingTo("X")
        }
    }
}