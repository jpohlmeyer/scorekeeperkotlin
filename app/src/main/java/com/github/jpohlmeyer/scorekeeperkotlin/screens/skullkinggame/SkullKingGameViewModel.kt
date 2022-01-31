package com.github.jpohlmeyer.scorekeeperkotlin.screens.skullkinggame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.SkullKingGame
import com.github.jpohlmeyer.scorekeeperkotlin.model.SkullKingPlayer
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

}