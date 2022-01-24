package com.github.jpohlmeyer.scorekeeperkotlin.screens.start

import androidx.lifecycle.ViewModel
import com.github.jpohlmeyer.scorekeeperkotlin.GameService
import com.github.jpohlmeyer.scorekeeperkotlin.model.GameType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    val gameService: GameService
) : ViewModel() {
    val gameList : List<GameType> = GameType.values().toList()


}