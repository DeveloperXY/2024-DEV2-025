package com.developerxy.bnp_tic_tac_toe

import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import org.junit.Before
import org.junit.Test

class GameStateTests {

    private lateinit var gameState: GameState

    @Before
    fun setUp() {
        gameState = GameState()
    }

    @Test
    fun starterPlayerShouldBeXByDefault() {
        gameState.currentPlayer.shouldBeEqualComparingTo("X")
    }
}