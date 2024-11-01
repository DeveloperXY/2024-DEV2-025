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
            status.shouldBeEqualComparingTo(GameStatus.ONGOING)
        }
    }

    @Test
    fun `Making a move updates the game board and toggles current player`() {
        gameState.apply {
            // Place an X initially
            (0 to 0).let { coords ->
                makeMove(at = coords)
                gameBoard.markAt(coords).shouldBeEqualComparingTo("X")
                currentPlayer.shouldBeEqualComparingTo("O")
            }

            // Place an O
            (1 to 1).let { coords ->
                makeMove(at = coords)
                gameBoard.markAt(coords).shouldBeEqualComparingTo("O")
                currentPlayer.shouldBeEqualComparingTo("X")
            }
        }
    }

    @Test
    fun `Game status is ongoing by default`() {
        // TODO
        // TODO
        // TODO
        // TODO
        // TODO: move this test to the other first test
    }
}