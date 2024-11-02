package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.domain.model.GameStatus
import com.developerxy.bnp_tic_tac_toe.ui.GameViewModel
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
    val gameState = gameState.value
    gameState.apply {
        assertEquals("X", currentPlayer)
        assertTrue(gameBoard.isClear())
        assertEquals(GameStatus.ONGOING, status)
    }
}