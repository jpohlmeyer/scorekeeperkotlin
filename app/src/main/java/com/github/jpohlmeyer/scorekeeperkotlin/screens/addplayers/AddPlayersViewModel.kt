package com.github.jpohlmeyer.scorekeeperkotlin.screens.addplayers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player

class AddPlayersViewModel : ViewModel() {
    var playerLiveData: MutableLiveData<MutableList<Player>> = MutableLiveData()
    private var playerList: MutableList<Player> = mutableListOf()

    init {
        playerLiveData.value = playerList
    }
}