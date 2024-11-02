package com.developerxy.bnp_tic_tac_toe.domain.model

data class GameState(
    val gameBoard: GameBoard = GameBoard(),
    var currentPlayer: String = "X",
    var status: GameStatus = GameStatus.ONGOING
)