package com.github.jpohlmeyer.scorekeeperkotlin.model.simplegame

import com.github.jpohlmeyer.scorekeeperkotlin.model.Game
import com.github.jpohlmeyer.scorekeeperkotlin.model.Player
import kotlin.reflect.KClass

class SimpleGame : Game() {
    override val playerType: KClass<out Player> = SimplePlayer::class

    val playerList: MutableList<SimplePlayer>
        get() {
            // TODO are checks necessary? else case should not happen...
            if (genericPlayerList.isEmpty() || genericPlayerList[0] is SimplePlayer) {
                @Suppress("UNCHECKED_CAST")
                return genericPlayerList as MutableList<SimplePlayer>
            } else {
                return mutableListOf()
            }
        }
}