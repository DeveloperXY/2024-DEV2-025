package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.domain.GameState
import com.developerxy.bnp_tic_tac_toe.domain.GameStatus
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import junit.framework.Assert.assertEquals
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
    fun `Test vertical win on the left column`() {
        /*
            X - O -
            X -   - O
            X -   -
         */
        gameState.apply {
            val steps = listOf(
                { makeMove(0 to 0) },
                { makeMove(0 to 1) },
                { makeMove(1 to 0) },
                { makeMove(1 to 2) },
            )

            steps.forEach {
                it()
                assertEquals(GameStatus.ONGOING, status)
            }

            // Final X move
            makeMove(2 to 0)

            assertEquals(GameStatus.X_WON, status)
        }
    }

    @Test
    fun `Test vertical win on the middle column`() {
        /*
              - X -
            O - X - O
              - X -
         */
        gameState.apply {
            val steps = listOf(
                { makeMove(0 to 1) },
                { makeMove(1 to 0) },
                { makeMove(1 to 1) },
                { makeMove(1 to 2) },
            )

            steps.forEach {
                it()
                assertEquals(GameStatus.ONGOING, status)
            }

            // Final X move
            makeMove(2 to 1)

            assertEquals(GameStatus.X_WON, status)
        }
    }

    @Test
    fun `Test vertical win on the right column`() {
        /*
              -   - X
            O -   - X
              - O - X
         */
        gameState.apply {
            val steps = listOf(
                { makeMove(0 to 2) },
                { makeMove(1 to 0) },
                { makeMove(1 to 2) },
                { makeMove(2 to 1) },
            )

            steps.forEach {
                it()
                assertEquals(GameStatus.ONGOING, status)
            }

            // Final X move
            makeMove(2 to 2)

            assertEquals(GameStatus.X_WON, status)
        }
    }

    @Test
    fun `Test horizontal win on the top row`() {
        /*
            X - X - X
              - O  - O
              -   -
         */
        gameState.apply {
            val steps = listOf(
                { makeMove(0 to 0) },
                { makeMove(1 to 1) },
                { makeMove(0 to 1) },
                { makeMove(1 to 2) },
            )

            steps.forEach {
                it()
                assertEquals(GameStatus.ONGOING, status)
            }

            // Final X move
            makeMove(0 to 2)

            assertEquals(GameStatus.X_WON, status)
        }
    }

    @Test
    fun `Test horizontal win on the middle row`() {
        /*
              - O -
            X - X - X
              - O -
         */
        gameState.apply {
            val steps = listOf(
                { makeMove(1 to 0) },
                { makeMove(0 to 1) },
                { makeMove(1 to 1) },
                { makeMove(2 to 1) },
            )

            steps.forEach {
                it()
                assertEquals(GameStatus.ONGOING, status)
            }

            // Final X move
            makeMove(1 to 2)

            assertEquals(GameStatus.X_WON, status)
        }
    }

    @Test
    fun `Test horizontal win on the bottom row`() {
        /*
              -   -
            O - O -
            X - X - X
         */
        gameState.apply {
            val steps = listOf(
                { makeMove(2 to 0) },
                { makeMove(1 to 0) },
                { makeMove(2 to 1) },
                { makeMove(1 to 1) },
            )

            steps.forEach {
                it()
                assertEquals(GameStatus.ONGOING, status)
            }

            // Final X move
            makeMove(2 to 2)

            assertEquals(GameStatus.X_WON, status)
        }
    }

    @Test
    fun `Test diagonal from top left to bottom right`() {
        /*
            X -   -
            O - X -
            O -   - X
         */
        gameState.apply {
            val steps = listOf(
                { makeMove(0 to 0) },
                { makeMove(1 to 0) },
                { makeMove(1 to 1) },
                { makeMove(2 to 0) },
            )

            steps.forEach {
                it()
                assertEquals(GameStatus.ONGOING, status)
            }

            // Final X move
            makeMove(2 to 2)

            assertEquals(GameStatus.X_WON, status)
        }
    }

    @Test
    fun `Test diagonal from top right to bottom left`() {
        /*
              -   - X
            O - X -
            X -   - O
         */
        gameState.apply {
            val steps = listOf(
                { makeMove(0 to 2) },
                { makeMove(1 to 0) },
                { makeMove(1 to 1) },
                { makeMove(2 to 2) },
            )

            steps.forEach {
                it()
                assertEquals(GameStatus.ONGOING, status)
            }

            // Final X move
            makeMove(2 to 0)

            assertEquals(GameStatus.X_WON, status)
        }
    }
}