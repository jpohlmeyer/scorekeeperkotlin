package com.github.jpohlmeyer.scorekeeperkotlin

import com.github.jpohlmeyer.scorekeeperkotlin.model.Game
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimpleGame
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameService @Inject constructor() {
    // TODO no init?
    var game: Game = SimpleGame()
}