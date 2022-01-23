package com.github.jpohlmeyer.scorekeeperkotlin.screens.game

import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player

class GameViewModel : ViewModel() {
    var playerList: MutableList<Player> = mutableListOf()
}