package com.github.jpohlmeyer.scorekeeperkotlin.model

class SimplePlayer (
    name: String,
) : Player(name) {
    var points: Int = 0
}