package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.domain.model.GameBoard
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import org.junit.Before
import org.junit.Test

class GameBoardTests {

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

        gameBoard.grid[coords.first][coords.second] = mark
        gameBoard.markAt(coords).shouldBeEqualComparingTo(mark)
    }

    @Test
    fun placingNewMarkOutOfGridBoundsThrowsError() {
        val maxRow = 2
        val maxCol = 2

        // Place marks in the [-1..3][-1..3] coordinate ranges
        for (i in -1..maxRow + 1) {
            for (j in -1..maxCol + 1) {
                val placeMark = {
                    gameBoard.grid[i][j] = "O"
                }

                val coordsOutOfBounds = i < 0 || j < 0 || i > maxRow || j > maxCol
                if (coordsOutOfBounds)
                    shouldThrow<ArrayIndexOutOfBoundsException> { placeMark() }
                else
                    shouldNotThrow<ArrayIndexOutOfBoundsException> { placeMark() }
            }
        }
    }
}