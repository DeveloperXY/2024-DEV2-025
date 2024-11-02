package com.developerxy.bnp_tic_tac_toe.domain.usecase

import com.developerxy.bnp_tic_tac_toe.domain.model.GameState

class StartNewGameUseCase {
    operator fun invoke(firstPlayerToBegin: String): GameState = GameState(currentPlayer = firstPlayerToBegin)
}