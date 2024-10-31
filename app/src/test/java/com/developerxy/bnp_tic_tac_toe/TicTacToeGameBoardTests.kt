package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.domain.GameBoard
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
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

    @Test
    fun gameBoardIsUpdatedUponPlacingMark() {
        val coords = 1 to 1
        val mark = "X"

        gameBoard.placeMark(mark, at = coords)
        gameBoard.markAt(coords).shouldBeEqualComparingTo(mark)
    }
}