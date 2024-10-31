package com.developerxy.bnp_tic_tac_toe.domain

const val STARTER_PLAYER = "X"

class GameState(
    val currentPlayer: String = STARTER_PLAYER,
    val gameBoard: GameBoard = GameBoard()
)