package com.github.jpohlmeyer.scorekeeperkotlin.screens.game

import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.model.SimplePlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    var playerList: MutableList<SimplePlayer> = mutableListOf()
}