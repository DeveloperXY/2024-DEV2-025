package com.developerxy.bnp_tic_tac_toe.ui

import com.developerxy.bnp_tic_tac_toe.domain.model.GameStatus

// The game board illustrations assume that X always begin first

fun GameViewModel.simulateDraw() {
    /*
        X - O - X
        O - O - X
        X - X - O
     */
    val steps = listOf(
        { makeMove(0 to 0) },
        { makeMove(0 to 1) },
        { makeMove(0 to 2) },
        { makeMove(1 to 0) },
        { makeMove(1 to 2) },
        { makeMove(1 to 1) },
        { makeMove(2 to 0) },
        { makeMove(2 to 2) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(2 to 1)
}

fun GameViewModel.simulateVerticalWinOnLeftColumn() {
    /*
       X - O -
       X -   - O
       X -   -
    */
    val steps = listOf(
        { makeMove(0 to 0) },
        { makeMove(0 to 1) },
        { makeMove(1 to 0) },
        { makeMove(1 to 2) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(2 to 0)
}

fun GameViewModel.simulateVerticalWinOnMiddleColumn() {
    /*
         - X -
       O - X - O
         - X -
    */
    val steps = listOf(
        { makeMove(0 to 1) },
        { makeMove(1 to 0) },
        { makeMove(1 to 1) },
        { makeMove(1 to 2) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(2 to 1)
}

fun GameViewModel.simulateVerticalWinOnRightColumn() {
    /*
         -   - X
       O -   - X
         - O - X
    */
    val steps = listOf(
        { makeMove(0 to 2) },
        { makeMove(1 to 0) },
        { makeMove(1 to 2) },
        { makeMove(2 to 1) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(2 to 2)
}

fun GameViewModel.simulateHorizontalWinOnTopRow() {
    /*
       X - X - X
         - O  - O
         -   -
    */
    val steps = listOf(
        { makeMove(0 to 0) },
        { makeMove(1 to 1) },
        { makeMove(0 to 1) },
        { makeMove(1 to 2) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(0 to 2)
}

fun GameViewModel.simulateHorizontalWinOnMiddleRow() {
    /*
         - O -
       X - X - X
         - O -
    */
    val steps = listOf(
        { makeMove(1 to 0) },
        { makeMove(0 to 1) },
        { makeMove(1 to 1) },
        { makeMove(2 to 1) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(1 to 2)
}

fun GameViewModel.simulateHorizontalWinOnBottomRow() {
    /*
         -   -
       O - O -
       X - X - X
    */
    val steps = listOf(
        { makeMove(2 to 0) },
        { makeMove(1 to 0) },
        { makeMove(2 to 1) },
        { makeMove(1 to 1) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(2 to 2)
}

fun GameViewModel.simulateDiagonalWinFromTopLeftToBottomRight() {
    /*
       X -   -
       O - X -
       O -   - X
    */
    val steps = listOf(
        { makeMove(0 to 0) },
        { makeMove(1 to 0) },
        { makeMove(1 to 1) },
        { makeMove(2 to 0) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(2 to 2)
}

fun GameViewModel.simulateDiagonalWinFromTopRightToBottomLeft() {
    /*
              -   - X
            O - X -
            X -   - O
         */

    val steps = listOf(
        { makeMove(0 to 2) },
        { makeMove(1 to 0) },
        { makeMove(1 to 1) },
        { makeMove(2 to 2) },
    )

    steps.forEach {
        it()
        assertCurrentGameStatusMatches(GameStatus.ONGOING)
    }

    makeMove(2 to 0)
}