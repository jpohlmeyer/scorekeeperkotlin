package com.github.jpohlmeyer.scorekeeperkotlin.model.skullkinggame

import com.github.jpohlmeyer.scorekeeperkotlin.model.Player

class SkullKingPlayer(
    name: String
) : Player(name) {

    val rounds: Array<SkullKingRound> = Array(SkullKingGame.NUMBER_OF_ROUNDS) { i -> SkullKingRound(i)}

    val points: Int
        get() = calculatePoints()

    private fun calculatePoints(): Int {
        var resultPoints = 0
        for (round in rounds) {
            resultPoints += round.points
        }
        return resultPoints
    }
}