package com.developerxy.bnp_tic_tac_toe.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.developerxy.bnp_tic_tac_toe.domain.model.GameState
import com.developerxy.bnp_tic_tac_toe.domain.usecase.MakeMoveUseCase
import com.developerxy.bnp_tic_tac_toe.domain.usecase.StartNewGameUseCase

class GameViewModel(
    private val _makeMove: MakeMoveUseCase = MakeMoveUseCase(),
    private val _startNewGame: StartNewGameUseCase = StartNewGameUseCase(),
    private val firstPlayerToBegin: String = "X"
) : ViewModel() {

    private val _gameState = mutableStateOf(_startNewGame(firstPlayerToBegin))
    val gameState: State<GameState> get() = _gameState

    fun makeMove(at: Pair<Int, Int>) {
        _gameState.value = _makeMove(gameState = gameState.value, at)
    }

    fun restartGame() {
        _gameState.value = _startNewGame(
            firstPlayerToBegin = firstPlayerToBegin
        )
    }
}
