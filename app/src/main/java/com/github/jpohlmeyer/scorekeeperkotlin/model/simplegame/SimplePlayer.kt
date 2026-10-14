package com.github.jpohlmeyer.scorekeeperkotlin.model.simplegame

import com.github.jpohlmeyer.scorekeeperkotlin.model.Player

class SimplePlayer (
    name: String
) : Player(name) {
    override var points: Int = 0
}