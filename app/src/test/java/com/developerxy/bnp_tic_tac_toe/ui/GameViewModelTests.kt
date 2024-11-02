package com.developerxy.bnp_tic_tac_toe.ui

import com.developerxy.bnp_tic_tac_toe.domain.model.GameStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
        listOf(
            GameViewModel(firstPlayerToBegin = "X"),
            GameViewModel(firstPlayerToBegin = "O")
        ).forEach { it.assertInitialGameStateIsCorrect() }
    }

    @Test
    fun `First player to begin the game is correctly set`() {
        with(GameViewModel(firstPlayerToBegin = "O")) {
            assertCurrentPlayerIs("O")
        }
        with(GameViewModel(firstPlayerToBegin = "X")) {
            assertCurrentPlayerIs("X")
        }
        with(GameViewModel()) {
            assertCurrentPlayerIs("X")
        }
    }

    @Test
    fun `Making a move updates the game board and toggles current player`() {
        with(GameViewModel(firstPlayerToBegin = "O")) {
            (1 to 1).let { coords ->
                makeMove(at = coords)

                val gameState = gameState.value
                assertEquals("O", gameState.gameBoard.markAt(coords))
                assertEquals("X", gameState.currentPlayer)
            }
        }
        with(GameViewModel()) {
            (0 to 0).let { coords ->
                makeMove(at = coords)

                val gameState = gameState.value
                assertEquals("X", gameState.gameBoard.markAt(coords))
                assertEquals("O", gameState.currentPlayer)
            }
        }
    }

    @Test
    fun `Test vertical win on the left column`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateVerticalWinOnLeftColumn()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateVerticalWinOnLeftColumn()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
        }
    }

    @Test
    fun `Test vertical win on the middle column`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateVerticalWinOnMiddleColumn()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateVerticalWinOnMiddleColumn()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
        }
    }

    @Test
    fun `Test vertical win on the right column`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateVerticalWinOnRightColumn()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateVerticalWinOnRightColumn()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
        }
    }

    @Test
    fun `Test horizontal win on the top row`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateHorizontalWinOnTopRow()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateHorizontalWinOnTopRow()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
        }
    }

    @Test
    fun `Test horizontal win on the middle row`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateHorizontalWinOnMiddleRow()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateHorizontalWinOnMiddleRow()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
        }
    }

    @Test
    fun `Test horizontal win on the bottom row`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateHorizontalWinOnBottomRow()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateHorizontalWinOnBottomRow()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
        }
    }

    @Test
    fun `Test diagonal win from top left to bottom right`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateDiagonalWinFromTopLeftToBottomRight()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateDiagonalWinFromTopLeftToBottomRight()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
        }
    }

    @Test
    fun `Test diagonal win from top right to bottom left`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateDiagonalWinFromTopRightToBottomLeft()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateDiagonalWinFromTopRightToBottomLeft()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
        }
    }

    @Test
    fun `Test draw`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateDraw()
            assertCurrentGameStatusMatches(GameStatus.DRAW)
        }
        with(GameViewModel(firstPlayerToBegin = "O")) {
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
    fun `Restarting the game after a draw resets its state correctly`() {
        with(gameViewModel) {
            simulateDraw()
            assertCurrentGameStatusMatches(GameStatus.DRAW)
            restartGame()
            assertInitialGameStateIsCorrect()
        }
    }

    @Test
    fun `Restarting the game after player X wins resets its state correctly`() {
        with(gameViewModel) {
            simulateHorizontalWinOnBottomRow()
            assertCurrentGameStatusMatches(GameStatus.X_WON)
            restartGame()
            assertInitialGameStateIsCorrect()
        }
    }

    @Test
    fun `Restarting the game after player O wins resets its state correctly`() {
        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateHorizontalWinOnBottomRow()
            assertCurrentGameStatusMatches(GameStatus.O_WON)
            restartGame()
            assertInitialGameStateIsCorrect()
        }
    }

    @Test
    fun `Making a move after a draw does nothing`() {
        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateDraw()
            assertCurrentPlayerIs("X")
            makeMove(0 to 0)
            assertCurrentPlayerIs("X")
        }

        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateDraw()
            assertCurrentPlayerIs("O")
            makeMove(0 to 0)
            assertCurrentPlayerIs("O")
        }
    }

    @Test
    fun `Making a move after player X or O wins does nothing`() {
        with(GameViewModel(firstPlayerToBegin = "X")) {
            simulateVerticalWinOnRightColumn()
            assertCurrentGameStatusMatches(GameStatus.X_WON)

            val gameBoard = gameState.value.gameBoard
            val coords = 0 to 0

            assertCurrentPlayerIs("O")
            assertTrue(gameBoard.isCellEmptyAt(coords))

            makeMove(coords)

            assertCurrentPlayerIs("O")
            assertTrue(gameBoard.isCellEmptyAt(coords))
        }

        with(GameViewModel(firstPlayerToBegin = "O")) {
            simulateVerticalWinOnRightColumn()
            assertCurrentGameStatusMatches(GameStatus.O_WON)

            val gameBoard = gameState.value.gameBoard
            val coords = 0 to 0

            assertCurrentPlayerIs("X")
            assertTrue(gameBoard.isCellEmptyAt(coords))

            makeMove(coords)

            assertCurrentPlayerIs("X")
            assertTrue(gameBoard.isCellEmptyAt(coords))
        }
    }
}