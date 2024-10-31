package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.domain.GameBoard
import io.kotest.assertions.throwables.shouldThrow
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

    @Test
    fun placingNewMarkOutOfGridBoundsThrowsError() {
        val maxRow = 2; val maxCol = 2

        // Place a mark in the [0..2] ranges
        for (i in 0 until maxRow) {
            for (j in 0 until maxCol) {
                gameBoard.placeMark(mark = "O", at = i to j)
            }
        }

        val outOfBoundsCoords = (maxRow + 1 to maxCol + 1)
        shouldThrow<Exception> {
            gameBoard.placeMark(mark = "X", at = outOfBoundsCoords)
        }
    }
}