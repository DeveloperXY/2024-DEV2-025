package com.developerxy.bnp_tic_tac_toe.ui

import com.developerxy.bnp_tic_tac_toe.domain.model.GameStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

fun GameViewModel.assertCurrentGameStatusMatches(gameStatus: GameStatus) {
    val gameState = gameState.value
    assertEquals(gameStatus, gameState.status)
}

fun GameViewModel.assertCurrentPlayerIs(player: String) {
    val gameState = gameState.value
    assertEquals(player, gameState.currentPlayer)
}

fun GameViewModel.assertInitialGameStateIsCorrect() {
    val firstPlayer = firstPlayerToBegin
    val gameState = gameState.value

    gameState.apply {
        assertEquals(firstPlayer, currentPlayer)
        assertTrue(gameBoard.isClear())
        assertEquals(GameStatus.ONGOING, status)
    }
}