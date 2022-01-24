package com.github.jpohlmeyer.scorekeeperkotlin.screens.game

import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.Game
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimpleGame
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimplePlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    gameService: GameService
) : ViewModel() {
    var game: Game = gameService.game

    fun simpleGame() : SimpleGame{
        return game as SimpleGame
    }

    //TODO somehow make this work with different gametypes
}