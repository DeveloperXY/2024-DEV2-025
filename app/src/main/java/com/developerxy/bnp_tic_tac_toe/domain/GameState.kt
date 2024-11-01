package com.developerxy.bnp_tic_tac_toe.domain

class GameState(
    val gameBoard: GameBoard = GameBoard(),
    firstPlayer: String = "X"
) {
    var currentPlayer: String = firstPlayer
        private set
    var status: GameStatus = GameStatus.ONGOING
        private set

    fun makeMove(at: Pair<Int, Int>) {
        if (!gameBoard.isCellEmptyAt(at))
            return

        gameBoard.placeMark(mark = currentPlayer, at)

        val didCurrentPlayerWin = checkForWin()
        if (didCurrentPlayerWin) {
            status = if (currentPlayer == "X") {
                GameStatus.X_WON
            } else {
                GameStatus.O_WON
            }
        } else {
            if (gameBoard.isSaturated()) {
                // Grid is saturated and no one won, then it's a draw
                status = GameStatus.DRAW
            } else {
                toggleCurrentPlayer()
            }
        }
    }

    private fun toggleCurrentPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }

    private fun checkForWin(): Boolean {
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