package com.developerxy.bnp_tic_tac_toe.domain

class GameBoard(
    val grid: Array<Array<String>> = Array(3) { Array(3) { "" } }
) {
    fun isClear(): Boolean {
        return grid.all { gridRow -> gridRow.all { gridCell -> gridCell.isEmpty() } }
    }
}