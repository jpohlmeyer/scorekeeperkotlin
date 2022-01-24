package com.github.jpohlmeyer.scorekeeperkotlin.model

import kotlin.reflect.KClass

class SimpleGame : Game() {
    override val playerType: KClass<out Player> = SimplePlayer::class

    val playerList: MutableList<SimplePlayer>
        get() {
            if (genericPlayerList.isEmpty() || genericPlayerList[0] is SimplePlayer) {
                @Suppress("UNCHECKED_CAST")
                return genericPlayerList as MutableList<SimplePlayer>
            } else {
                return mutableListOf()
            }
        }
}