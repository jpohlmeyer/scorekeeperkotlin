package com.github.jpohlmeyer.scorekeeperkotlin.screens.start

import androidx.lifecycle.ViewModel

class StartViewModel : ViewModel() {

    // TODO how to fill from Model? LiveData?
    val gameList : MutableList<String> = mutableListOf("Simple Game")

}