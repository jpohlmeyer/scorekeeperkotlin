package com.github.jpohlmeyer.scorekeeperkotlin.screens.endgame

import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameEndViewModel @Inject constructor(
    gameService: GameService
) : ViewModel() {
    val players: List<Player> = gameService.game.genericPlayerList.sortedByDescending { it.points }
}
