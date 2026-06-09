package com.github.jpohlmeyer.scorekeeperkotlin.screens.skullkinggame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.skullkinggame.SkullKingGame
import com.github.jpohlmeyer.scorekeeperkotlin.model.skullkinggame.SkullKingPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SkullKingGameViewModel @Inject constructor(
    gameService: GameService
) : ViewModel() {
    private var game: SkullKingGame = gameService.game as SkullKingGame

    private val _playerLiveData: MutableList<MutableLiveData<SkullKingPlayer>> = mutableListOf()
    val playerLiveData: List<LiveData<SkullKingPlayer>>
        get() = _playerLiveData

    private var playerList = game.playerList

    init {
        for (player in playerList) {
            _playerLiveData.add(MutableLiveData(player))
        }
    }

    fun setTricksGuessed(playerIndex: Int, tricks: Int, round: Int) {
        playerList[playerIndex].rounds[round].tricksGuessed = tricks
        _playerLiveData[playerIndex].value = _playerLiveData[playerIndex].value
    }

    fun setTricksGot(playerIndex: Int, tricks: Int, round: Int) {
        playerList[playerIndex].rounds[round].tricksGot = tricks
        _playerLiveData[playerIndex].value = _playerLiveData[playerIndex].value
    }

    fun setBonus(playerIndex: Int, bonus: Int, round: Int) {
        playerList[playerIndex].rounds[round].bonus = bonus
        _playerLiveData[playerIndex].value = _playerLiveData[playerIndex].value
    }
}