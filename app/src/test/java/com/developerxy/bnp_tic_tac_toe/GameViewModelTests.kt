package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.domain.model.GameStatus
import com.developerxy.bnp_tic_tac_toe.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameViewModelTests {

    private lateinit var gameViewModel: GameViewModel

    @Before
    fun setUp() {
        gameViewModel = GameViewModel()
    }

    @Test
    fun `Initial game state is correct`() {
        gameViewModel.assertInitialGameStateIsCorrect()
    }

    @Test
    fun `First player to begin the game is correctly set`() {
        val gameViewModel1 = GameViewModel(firstPlayerToBegin = "O")
        gameViewModel1.assertCurrentPlayerIs("O")

        val gameViewModel2 = GameViewModel(firstPlayerToBegin = "X")
        gameViewModel2.assertCurrentPlayerIs("X")

        val gameViewModel3 = GameViewModel()
        gameViewModel3.assertCurrentPlayerIs("X")
    }

    @Test
    fun `Making a move updates the game board and toggles current player`() {
        with(gameViewModel) {
            // Place an X initially
            (0 to 0).let { coords ->
                makeMove(at = coords)

                val gameState = gameState.value
                assertEquals("X", gameState.gameBoard.markAt(coords))
                assertEquals("O", gameState.currentPlayer)
            }

            // Place an O
            (1 to 1).let { coords ->
                makeMove(at = coords)

                val gameState = gameState.value
                assertEquals("O", gameState.gameBoard.markAt(coords))
                assertEquals("X", gameState.currentPlayer)
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
        with(gameViewModel) {
            val steps = listOf(
                { makeMove(0 to 0) },
                { makeMove(0 to 1) },
                { makeMove(1 to 0) },
                { makeMove(1 to 2) },
            )

            steps.forEach {
                it()
                assertCurrentGameStatusMatches(GameStatus.ONGOING)
            }

            // Final X move
            makeMove(2 to 0)
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }
    }

    @Test
    fun `Test vertical win on the middle column`() {
        /*
              - X -
            O - X - O
              - X -
         */
        with(gameViewModel) {
            val steps = listOf(
                { makeMove(0 to 1) },
                { makeMove(1 to 0) },
                { makeMove(1 to 1) },
                { makeMove(1 to 2) },
            )

            steps.forEach {
                it()
                assertCurrentGameStatusMatches(GameStatus.ONGOING)
            }

            // Final X move
            makeMove(2 to 1)
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }
    }

    @Test
    fun `Test vertical win on the right column`() {
        /*
              -   - X
            O -   - X
              - O - X
         */
        with(gameViewModel) {
            val steps = listOf(
                { makeMove(0 to 2) },
                { makeMove(1 to 0) },
                { makeMove(1 to 2) },
                { makeMove(2 to 1) },
            )

            steps.forEach {
                it()
                assertCurrentGameStatusMatches(GameStatus.ONGOING)
            }

            // Final X move
            makeMove(2 to 2)
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }
    }

    @Test
    fun `Test horizontal win on the top row`() {
        /*
            X - X - X
              - O  - O
              -   -
         */
        with(gameViewModel) {
            val steps = listOf(
                { makeMove(0 to 0) },
                { makeMove(1 to 1) },
                { makeMove(0 to 1) },
                { makeMove(1 to 2) },
            )

            steps.forEach {
                it()
                assertCurrentGameStatusMatches(GameStatus.ONGOING)
            }

            // Final X move
            makeMove(0 to 2)
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }
    }

    @Test
    fun `Test horizontal win on the middle row`() {
        /*
              - O -
            X - X - X
              - O -
         */
        with(gameViewModel) {
            val steps = listOf(
                { makeMove(1 to 0) },
                { makeMove(0 to 1) },
                { makeMove(1 to 1) },
                { makeMove(2 to 1) },
            )

            steps.forEach {
                it()
                assertCurrentGameStatusMatches(GameStatus.ONGOING)
            }

            // Final X move
            makeMove(1 to 2)
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }
    }

    @Test
    fun `Test horizontal win on the bottom row`() {
        /*
              -   -
            O - O -
            X - X - X
         */
        with(gameViewModel) {
            val steps = listOf(
                { makeMove(2 to 0) },
                { makeMove(1 to 0) },
                { makeMove(2 to 1) },
                { makeMove(1 to 1) },
            )

            steps.forEach {
                it()
                assertCurrentGameStatusMatches(GameStatus.ONGOING)
            }

            // Final X move
            makeMove(2 to 2)
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }
    }

    @Test
    fun `Test diagonal from top left to bottom right`() {
        /*
            X -   -
            O - X -
            O -   - X
         */
        with(gameViewModel) {
            val steps = listOf(
                { makeMove(0 to 0) },
                { makeMove(1 to 0) },
                { makeMove(1 to 1) },
                { makeMove(2 to 0) },
            )

            steps.forEach {
                it()
                assertCurrentGameStatusMatches(GameStatus.ONGOING)
            }

            // Final X move
            makeMove(2 to 2)
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }
    }

    @Test
    fun `Test diagonal from top right to bottom left`() {
        /*
              -   - X
            O - X -
            X -   - O
         */
        with(gameViewModel) {
            val steps = listOf(
                { makeMove(0 to 2) },
                { makeMove(1 to 0) },
                { makeMove(1 to 1) },
                { makeMove(2 to 2) },
            )

            steps.forEach {
                it()
                assertCurrentGameStatusMatches(GameStatus.ONGOING)
            }

            // Final X move
            makeMove(2 to 0)
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }
    }

    @Test
    fun `Test draw`() {
        gameViewModel.apply {
            simulateDraw()
            assertCurrentGameStatusMatches(GameStatus.DRAW)
        }
    }

    @Test
    fun `Placing a mark on an already occupied cell does nothing`() {
        with(gameViewModel) {
            assertCurrentPlayerIs("X")
            makeMove(0 to 0)
            assertCurrentPlayerIs("O")
            makeMove(0 to 0)
            assertCurrentPlayerIs("O")
        }
    }

    @Test
    fun `Restarting the game resets its state correctly`() {
        with(gameViewModel) {
            restartGame()
            assertInitialGameStateIsCorrect()
        }
    }

    private fun GameViewModel.simulateDraw() {
        /*
            X - O - X
            O - O - X
            X - X - O
         */
        val steps = listOf(
            { makeMove(0 to 0) },
            { makeMove(0 to 1) },
            { makeMove(0 to 2) },
            { makeMove(1 to 0) },
            { makeMove(1 to 2) },
            { makeMove(1 to 1) },
            { makeMove(2 to 0) },
            { makeMove(2 to 2) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        makeMove(2 to 1)
    }
}