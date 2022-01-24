package com.github.jpohlmeyer.scorekeeperkotlin.model

class SimpleGame : Game() {
    val playerList: MutableList<SimplePlayer> = mutableListOf()
}