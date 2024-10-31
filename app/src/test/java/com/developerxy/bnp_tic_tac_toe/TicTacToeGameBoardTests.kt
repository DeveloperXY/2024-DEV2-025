package com.developerxy.bnp_tic_tac_toe

import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.Before
import org.junit.Test

class TicTacToeGameBoardTests {

    private lateinit var gameBoard: GameBoard

    @Before
    fun setUp() {
        gameBoard = GameBoard()
    }

    @Test
    fun gameBoardIsInitiallyClearedOut() {
        gameBoard.isClear().shouldBeTrue()
    }
}