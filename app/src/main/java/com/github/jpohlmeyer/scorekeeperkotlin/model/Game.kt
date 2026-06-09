package com.github.jpohlmeyer.scorekeeperkotlin.model

import kotlin.reflect.KClass

abstract class Game {
    open val playerType: KClass<out Player> = Player::class
    val genericPlayerList: MutableList<Player> = mutableListOf()
}