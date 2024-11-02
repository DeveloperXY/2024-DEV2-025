package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.domain.model.GameStatus
import com.developerxy.bnp_tic_tac_toe.ui.GameViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
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
        assertInitialGameStateIsCorrect()
    }

    @Test
    fun `Making a move updates the game board and toggles current player`() {
        // Place an X initially
        (0 to 0).let { coords ->
            gameViewModel.makeMove(at = coords)

            val gameState = gameViewModel.gameState.value
            assertEquals("X", gameState.gameBoard.markAt(coords))
            assertEquals("O", gameState.currentPlayer)
        }

        // Place an O
        (1 to 1).let { coords ->
            gameViewModel.makeMove(at = coords)

            val gameState = gameViewModel.gameState.value
            assertEquals("O", gameState.gameBoard.markAt(coords))
            assertEquals("X", gameState.currentPlayer)
        }
    }

    @Test
    fun `Test vertical win on the left column`() {
        /*
            X - O -
            X -   - O
            X -   -
         */
        val steps = listOf(
            { gameViewModel.makeMove(0 to 0) },
            { gameViewModel.makeMove(0 to 1) },
            { gameViewModel.makeMove(1 to 0) },
            { gameViewModel.makeMove(1 to 2) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(2 to 0)

        assertCurrentGameStatusMatches(GameStatus.X_WON)
    }

    @Test
    fun `Test vertical win on the middle column`() {
        /*
              - X -
            O - X - O
              - X -
         */
        val steps = listOf(
            { gameViewModel.makeMove(0 to 1) },
            { gameViewModel.makeMove(1 to 0) },
            { gameViewModel.makeMove(1 to 1) },
            { gameViewModel.makeMove(1 to 2) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(2 to 1)

        assertCurrentGameStatusMatches(GameStatus.X_WON)
    }

    @Test
    fun `Test vertical win on the right column`() {
        /*
              -   - X
            O -   - X
              - O - X
         */
        val steps = listOf(
            { gameViewModel.makeMove(0 to 2) },
            { gameViewModel.makeMove(1 to 0) },
            { gameViewModel.makeMove(1 to 2) },
            { gameViewModel.makeMove(2 to 1) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(2 to 2)

        assertCurrentGameStatusMatches(GameStatus.X_WON)
    }

    @Test
    fun `Test horizontal win on the top row`() {
        /*
            X - X - X
              - O  - O
              -   -
         */
        val steps = listOf(
            { gameViewModel.makeMove(0 to 0) },
            { gameViewModel.makeMove(1 to 1) },
            { gameViewModel.makeMove(0 to 1) },
            { gameViewModel.makeMove(1 to 2) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(0 to 2)

        assertCurrentGameStatusMatches(GameStatus.X_WON)
    }

    @Test
    fun `Test horizontal win on the middle row`() {
        /*
              - O -
            X - X - X
              - O -
         */
        val steps = listOf(
            { gameViewModel.makeMove(1 to 0) },
            { gameViewModel.makeMove(0 to 1) },
            { gameViewModel.makeMove(1 to 1) },
            { gameViewModel.makeMove(2 to 1) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(1 to 2)

        assertCurrentGameStatusMatches(GameStatus.X_WON)
    }

    @Test
    fun `Test horizontal win on the bottom row`() {
        /*
              -   -
            O - O -
            X - X - X
         */
        val steps = listOf(
            { gameViewModel.makeMove(2 to 0) },
            { gameViewModel.makeMove(1 to 0) },
            { gameViewModel.makeMove(2 to 1) },
            { gameViewModel.makeMove(1 to 1) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(2 to 2)

        assertCurrentGameStatusMatches(GameStatus.X_WON)
    }

    @Test
    fun `Test diagonal from top left to bottom right`() {
        /*
            X -   -
            O - X -
            O -   - X
         */
        val steps = listOf(
            { gameViewModel.makeMove(0 to 0) },
            { gameViewModel.makeMove(1 to 0) },
            { gameViewModel.makeMove(1 to 1) },
            { gameViewModel.makeMove(2 to 0) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(2 to 2)

        assertCurrentGameStatusMatches(GameStatus.X_WON)
    }

    @Test
    fun `Test diagonal from top right to bottom left`() {
        /*
              -   - X
            O - X -
            X -   - O
         */
        val steps = listOf(
            { gameViewModel.makeMove(0 to 2) },
            { gameViewModel.makeMove(1 to 0) },
            { gameViewModel.makeMove(1 to 1) },
            { gameViewModel.makeMove(2 to 2) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(2 to 0)

        assertCurrentGameStatusMatches(GameStatus.X_WON)
    }

    @Test
    fun `Test draw`() {
        /*
            X - O - X
            O - O - X
            X - X - O
         */
        val steps = listOf(
            { gameViewModel.makeMove(0 to 0) },
            { gameViewModel.makeMove(0 to 1) },
            { gameViewModel.makeMove(0 to 2) },
            { gameViewModel.makeMove(1 to 0) },
            { gameViewModel.makeMove(1 to 2) },
            { gameViewModel.makeMove(1 to 1) },
            { gameViewModel.makeMove(2 to 0) },
            { gameViewModel.makeMove(2 to 2) },
        )

        steps.forEach {
            it()
            assertCurrentGameStatusMatches(GameStatus.ONGOING)
        }

        // Final X move
        gameViewModel.makeMove(2 to 1)

        assertCurrentGameStatusMatches(GameStatus.DRAW)
    }

    @Test
    fun `Placing a mark on an already occupied cell does nothing`() {
        assertCurrentPlayerIs("X")
        gameViewModel.makeMove(0 to 0)
        assertCurrentPlayerIs("O")
        gameViewModel.makeMove(0 to 0)
        assertCurrentPlayerIs("O")
    }

    @Test
    fun `Restarting the game resets its state correctly`() {
        gameViewModel.restartGame()
        assertInitialGameStateIsCorrect()
    }

    private fun assertCurrentGameStatusMatches(gameStatus: GameStatus) {
        val gameState = gameViewModel.gameState.value
        assertEquals(gameStatus, gameState.status)
    }

    private fun assertCurrentPlayerIs(player: String) {
        val gameState = gameViewModel.gameState.value
        assertEquals(player, gameState.currentPlayer)
    }

    private fun assertInitialGameStateIsCorrect() {
        val gameState = gameViewModel.gameState.value
        gameState.apply {
            assertEquals("X", currentPlayer)
            assertTrue(gameBoard.isClear())
            assertEquals(GameStatus.ONGOING, status)
        }
    }
}