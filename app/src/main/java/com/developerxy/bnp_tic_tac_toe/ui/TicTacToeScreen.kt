package com.developerxy.bnp_tic_tac_toe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerxy.bnp_tic_tac_toe.domain.model.GameBoard
import com.developerxy.bnp_tic_tac_toe.domain.model.GameStatus

@Composable
fun TicTacToeScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameState = gameViewModel.gameState.value
    val status = gameState.status
    val currentPlayer = gameState.currentPlayer

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Current Player: $currentPlayer",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        TicTacToeBoard(
            gameBoard = gameState.gameBoard,
            onCellClick = { row, col -> gameViewModel.makeMove(row to col) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when (status) {
                GameStatus.ONGOING -> "Game in progress"
                GameStatus.X_WON -> "Player X wins!"
                GameStatus.O_WON -> "Player O wins!"
                GameStatus.DRAW -> "DRAW"
            },
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { gameViewModel.restartGame() }) {
            Text("Start again")
        }
    }
}

@Composable
fun TicTacToeBoard(
    gameBoard: GameBoard,
    onCellClick: (Int, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
    ) {
        for (row in 0..2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (col in 0..2) {
                    TicTacToeCell(
                        mark = gameBoard.grid[row][col],
                        onClick = { onCellClick(row, col) }
                    )
                }
            }
        }
    }
}

@Composable
fun TicTacToeCell(
    mark: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .padding(4.dp)
            .background(Color.White)
            .clickable(enabled = mark.isEmpty()) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = mark,
            style = MaterialTheme.typography.headlineLarge,
            color = if (mark == "X") Color.Blue else Color.Red
        )
    }
}