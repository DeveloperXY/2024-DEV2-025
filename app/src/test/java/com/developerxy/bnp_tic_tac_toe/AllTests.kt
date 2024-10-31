package com.developerxy.bnp_tic_tac_toe

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(GameBoardTests::class, GameStateTests::class)
class AllTests