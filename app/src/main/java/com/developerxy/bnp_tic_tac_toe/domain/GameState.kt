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
        toggleCurrentPlayer()
    }

    private fun toggleCurrentPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }
}