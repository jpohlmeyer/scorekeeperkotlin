package com.github.jpohlmeyer.scorekeeperkotlin.model.skullkinggame

import kotlin.math.abs

class SkullKingRound(val roundNumber: Int) {
    var tricksGuessed: Int? = null
    var tricksGot: Int? = null
    var bonus: Int = 0

    val points: Int
        get() = calculatePoints()


    private fun calculatePoints() : Int {
        if (tricksGuessed != null && tricksGot != null) {
            if (tricksGuessed != tricksGot) {
                return (-1) * abs(tricksGuessed!! -tricksGot!!) * 10
            } else {
                if (tricksGuessed == 0) {
                    return roundNumber * 10
                } else {
                    return (tricksGuessed!! * 20) + bonus
                }
            }
        } else {
            return 0
        }
    }

}