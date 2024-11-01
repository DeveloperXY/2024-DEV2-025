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
        gameBoard.placeMark(mark = currentPlayer, at)

        val didCurrentPlayerWin = checkForWin()
        if (didCurrentPlayerWin) {
            status = if (currentPlayer == "X") {
                GameStatus.X_WON
            } else {
                GameStatus.O_WON
            }
        } else {
            toggleCurrentPlayer()
        }
    }

    private fun toggleCurrentPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }

    private fun checkForWin(): Boolean {
        fun isHorizontalWin(row: Int) = gameBoard.grid[row].all { it == currentPlayer }
        fun isVerticalWin(col: Int) = gameBoard.grid.map { it[col] }.all { it == currentPlayer }

        for (i in 0..2) {
            if (isHorizontalWin(i) || isVerticalWin(i)) return true
        }

        return false
    }
}