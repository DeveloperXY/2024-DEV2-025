package com.developerxy.bnp_tic_tac_toe.domain

class GameBoard(
    val grid: Array<Array<String>> = Array(3) { Array(3) { "" } }
) {
    fun isClear(): Boolean {
        return grid.all { gridRow -> gridRow.all { gridCell -> gridCell.isEmpty() } }
    }

    fun placeMark(mark: String, at: Pair<Int, Int>) {
        val (row, col) = at
        grid[row][col] = mark
    }

    fun markAt(coords: Pair<Int, Int>): String = grid[coords.first][coords.second]

    fun isCellEmptyAt(coords: Pair<Int, Int>): Boolean = markAt(coords).isEmpty()

    fun isSaturated(): Boolean = grid.all { row -> row.all { col -> col.isNotEmpty() } }
}