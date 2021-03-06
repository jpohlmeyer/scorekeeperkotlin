package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.Game
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddPlayersViewModel @Inject constructor(
    gameService: GameService
) : ViewModel() {
    var game: Game = gameService.game

    private val _playerLiveData: MutableLiveData<List<Player>> = MutableLiveData()
    val playerLiveData: LiveData<List<Player>>
        get() = _playerLiveData

    private val playerList: MutableList<Player> = gameService.game.genericPlayerList

    init {
        _playerLiveData.value = playerList
    }

    fun addPlayer(player: Player) {
        playerList.add(player)
        _playerLiveData.value = playerList
    }

    fun deletePlayer(position: Int) {
        playerList.removeAt(position)
        _playerLiveData.value = playerList
    }

    fun movePlayer(fromPosition: Int, toPosition: Int) {
        Collections.swap(playerList, fromPosition, toPosition)
        _playerLiveData.value = playerList
    }
}