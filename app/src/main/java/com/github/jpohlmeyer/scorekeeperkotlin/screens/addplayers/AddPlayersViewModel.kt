package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import java.util.*

class AddPlayersViewModel : ViewModel() {

    private val _playerLiveData: MutableLiveData<MutableList<Player>> = MutableLiveData()
    val playerLiveData: LiveData<MutableList<Player>>
        get() = _playerLiveData

    private val playerList: MutableList<Player> = mutableListOf()

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