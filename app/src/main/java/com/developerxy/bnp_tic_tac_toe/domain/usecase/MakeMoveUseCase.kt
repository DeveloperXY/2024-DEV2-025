package com.developerxy.bnp_tic_tac_toe.domain.usecase

import com.developerxy.bnp_tic_tac_toe.domain.model.GameBoard
import com.developerxy.bnp_tic_tac_toe.domain.model.GameState
import com.developerxy.bnp_tic_tac_toe.domain.model.GameStatus

class MakeMoveUseCase {
    operator fun invoke(gameState: GameState, at: Pair<Int, Int>): GameState {
        val currentBoard = gameState.gameBoard
        val currentPlayer = gameState.currentPlayer

        // TODO: check if game is ongoing
        if (!currentBoard.isCellEmptyAt(at))
            return gameState

        val updatedBoard = currentBoard.copy().apply {
            placeMark(currentPlayer, at)
        }

        val newGameStatus = gameState.calculateNewGameStatus()
        // TODO: don't toggle current player if game is no longer ongoing

        return gameState.copy(
            gameBoard = updatedBoard,
            currentPlayer = if (currentPlayer == "X") "O" else "X",
            status = newGameStatus
        )
    }

    private fun GameState.calculateNewGameStatus(): GameStatus {
        return if (didCurrentPlayerWin()) {
            if (currentPlayer == "X") {
                GameStatus.X_WON
            } else {
                GameStatus.O_WON
            }
        } else {
            if (gameBoard.isSaturated()) {
                // Grid is saturated and no one won, then it's a draw
                GameStatus.DRAW
            } else {
                GameStatus.ONGOING
            }
        }
    }

    private fun GameBoard.placeMark(mark: String, at: Pair<Int, Int>) {
        val (row, col) = at
        grid[row][col] = mark
    }

    private fun GameState.didCurrentPlayerWin(): Boolean {
        fun isHorizontalWin(row: Int) = gameBoard.grid[row].all { it == currentPlayer }
        fun isVerticalWin(col: Int) = gameBoard.grid.map { it[col] }.all { it == currentPlayer }
        fun isDiagonalWin(): Boolean = run {
            val topLeftToBottomRight =
                gameBoard.grid[0][0] == currentPlayer && gameBoard.grid[1][1] == currentPlayer && gameBoard.grid[2][2] == currentPlayer
            val topRightToBottomLeft =
                gameBoard.grid[0][2] == currentPlayer && gameBoard.grid[1][1] == currentPlayer && gameBoard.grid[2][0] == currentPlayer
            return topLeftToBottomRight || topRightToBottomLeft
        }

        for (i in 0..2) {
            if (isHorizontalWin(i) || isVerticalWin(i)) return true
        }

        return isDiagonalWin()
    }
}