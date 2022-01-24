package com.github.jpohlmeyer.scorekeeperkotlin.screens.game

import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimplePlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    val gameService: GameService
) : ViewModel() {
    var playerList: MutableList<Player> = mutableListOf()
}