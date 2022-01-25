package com.github.jpohlmeyer.scorekeeperkotlin.screens.simplegame

import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimpleGame
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimpleGameViewModel @Inject constructor(
    gameService: GameService
) : ViewModel() {
    var game: SimpleGame = gameService.game as SimpleGame
}