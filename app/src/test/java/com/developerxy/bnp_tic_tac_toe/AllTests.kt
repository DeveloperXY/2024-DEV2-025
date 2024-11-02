package com.developerxy.bnp_tic_tac_toe

import com.developerxy.bnp_tic_tac_toe.ui.GameViewModelTests
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(GameBoardTests::class, GameViewModelTests::class)
class AllTests