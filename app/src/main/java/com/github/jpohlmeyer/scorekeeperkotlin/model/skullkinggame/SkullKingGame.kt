package com.github.jpohlmeyer.scorekeeperkotlin.model.skullkinggame

import com.github.jpohlmeyer.scorekeeperkotlin.model.Game
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import kotlin.reflect.KClass

class SkullKingGame : Game() {

    companion object {
        val NUMBER_OF_ROUNDS = 10
    }

    override val playerType: KClass<out Player> = SkullKingPlayer::class

    val playerList: MutableList<SkullKingPlayer>
        get() {
            // TODO are checks necessary? else case should not happen...
            if (genericPlayerList.isEmpty() || genericPlayerList[0] is SkullKingPlayer) {
                @Suppress("UNCHECKED_CAST")
                return genericPlayerList as MutableList<SkullKingPlayer>
            } else {
                return mutableListOf()
            }
        }
}