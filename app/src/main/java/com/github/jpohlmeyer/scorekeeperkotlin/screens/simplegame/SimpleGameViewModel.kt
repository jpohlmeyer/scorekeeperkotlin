package com.github.jpohlmeyer.scorekeeperkotlin.screens.simplegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.simplegame.SimpleGame
import com.github.jpohlmeyer.scorekeeperkotlin.model.simplegame.SimplePlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimpleGameViewModel @Inject constructor(
    gameService: GameService
) : ViewModel() {
    private var game: SimpleGame = gameService.game as SimpleGame

    private val _playerLiveData: MutableList<MutableLiveData<SimplePlayer>> = mutableListOf()
    val playerLiveData: List<LiveData<SimplePlayer>>
        get() = _playerLiveData

    private var playerList = game.playerList

    init {
        for (player in playerList) {
            _playerLiveData.add(MutableLiveData(player))
        }
    }

    fun addPoints(playerIndex: Int, points: Int) {
        playerList[playerIndex].points += points
        _playerLiveData[playerIndex].value = _playerLiveData[playerIndex].value
    }

}