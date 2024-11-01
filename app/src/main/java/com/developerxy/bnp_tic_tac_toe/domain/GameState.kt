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
        for (i in 0..2) {
            if (gameBoard.grid.map { it[i] }.all { it == currentPlayer }) return true
        }

        return false
    }
}